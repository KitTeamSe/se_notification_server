package com.se_notification_server.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

//@Configuration
@Service
public class FirebaseConfig {

    @Value("classpath:keystore/se-notification-firebase-adminsdk.json")
    private Resource resource;

    @PostConstruct
    public void initFirebase() {
        try {
            // serviceAccount를 이용하여 Fireabse Admin SDK 초기화
            FileInputStream serviceAccount = new FileInputStream(resource.getFile());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }

}