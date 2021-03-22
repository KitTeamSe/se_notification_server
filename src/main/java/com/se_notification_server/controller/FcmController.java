package com.se_notification_server.controller;


import com.google.firebase.messaging.*;
import com.se_notification_server.domain.AccountTagMapping;
import com.se_notification_server.service.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FcmController {

    private final FcmService fcmService;

    @Autowired
    public FcmController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("notice/save-token")
    @ResponseBody
    public void sendToToken(@RequestParam("userId") Long userId, @RequestParam("token") String token) {
        AccountTagMapping accountTagMapping = new AccountTagMapping();
        accountTagMapping.setAccountId(userId);
        accountTagMapping.setToken(token);
        fcmService.save(accountTagMapping);
    }

    @PostMapping("notice/message")
    @ResponseBody
    public String sendToMessage(@RequestParam("userId") Long userId,  @RequestParam("title") String title,@RequestParam("msg") String msg) throws FirebaseMessagingException {

        String registrationToken = fcmService.getToken(userId);

        // 메시지 작성
        Message message = Message.builder()
                .putData("title", title)
                .putData("content", msg)
                .setToken(registrationToken)
                .build();

        // 등록토큰에 해당하는 장치에 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);

        // 응답
        System.out.println("Successfully sent message: " + response);

        return response;
    }



    @PostMapping("notice/multi-message")
    @ResponseBody
    public BatchResponse sendToMultiToken(@RequestParam("accountIdList") List<Long> accountIdList, @RequestParam("title") String title, @RequestParam("msg") String msg) throws FirebaseMessagingException {

        List<String> registrationTokens = fcmService.getTokenList(accountIdList);

        MulticastMessage message = MulticastMessage.builder()
                .putData("title", title)
                .putData("content", msg)
                .addAllTokens(registrationTokens)
                .build();

        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

        System.out.println(response.getSuccessCount() + " messages were sent successfully");

        return response;
    }

}