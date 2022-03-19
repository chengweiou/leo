package chengweiou.universe.leob.service;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.leob.manager.FcmManager;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("test")
public class PushTest {
    @Autowired
    private FcmManager manager;

    @Test
    public void sendNotiByToken() throws FailException {
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(Arrays.asList("euBVH4S0AJU:APA91bE-NC4QZXlAEJIFDCoVcI02cbnoI4AiylWuPLAIUPATjIZyQc_e0zVZItrhm_-J-jolJkLm0WDsEouasrXkqpGO_SlWKOJNfRagIu3MWRVm1VGXEnlBvqD324YnDjtUrkBRyoQo"))
                .setNotification(Notification.builder().setTitle("title-test").setBody("body-test").build()
            ).build();
        Integer successCount = manager.send(message);
        System.out.println(successCount);
        Assertions.assertEquals(1, successCount);
    }

    @Test
    public void sendMessageByToken() throws FailException {
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(Arrays.asList("euBVH4S0AJU:APA91bE-NC4QZXlAEJIFDCoVcI02cbnoI4AiylWuPLAIUPATjIZyQc_e0zVZItrhm_-J-jolJkLm0WDsEouasrXkqpGO_SlWKOJNfRagIu3MWRVm1VGXEnlBvqD324YnDjtUrkBRyoQo"))
                .putData("score", "2.3").putData("time", "3:20").build();
        Integer successCount = manager.send(message);
        Assertions.assertEquals(1, successCount);
    }

    @Test
    public void sendNotiByTopic() throws FailException {
        Message message = Message.builder()
                .setTopic("weather")
                .setNotification(Notification.builder().setTitle("title-test-topic").setBody("body-test-topic").build()
            ).build();
        String response = manager.send(message);
        System.out.println(response);
    }
}
