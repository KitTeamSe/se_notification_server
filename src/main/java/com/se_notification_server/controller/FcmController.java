package com.se_notification_server.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.se_notification_server.domain.AccountTokenMapping;
import com.se_notification_server.service.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        fcmService.save(AccountTokenMapping.builder()
                .accountId(userId)
                .token(token)
                .build());
    }

    @PostMapping("notice/send")
    @ResponseBody
    public void sendToMultiToken(@RequestParam("accountIdList") List<Long> accountIdList, @RequestParam("title") String title, @RequestParam("msg") String msg) throws FirebaseMessagingException {

        int max = 500;

        List<String> registrationTokens = fcmService.getTokenList(accountIdList);
        List<String> sendTokens;
        int n = registrationTokens.size()/max;

        for(int i=0; i<=n; i++) {
            if(i==n) {
                sendTokens = registrationTokens.subList(max*i, registrationTokens.size()-1);
            }else{
                sendTokens = registrationTokens.subList(max*i, max*i+(max-1));
            }
            fcmService.send(sendTokens, title, msg);
        }

    }

}