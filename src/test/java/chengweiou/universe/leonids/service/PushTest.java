package chengweiou.universe.leonids.service;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.leonids.manager.FcmManager;
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
                .setNotification(new Notification("title-test", "body-test")).build();
        Integer failCount = manager.send(message);
        System.out.println(failCount);
        Assertions.assertEquals(0, failCount);
    }

    @Test
    public void sendMessageByToken() throws FailException {
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(Arrays.asList("euBVH4S0AJU:APA91bE-NC4QZXlAEJIFDCoVcI02cbnoI4AiylWuPLAIUPATjIZyQc_e0zVZItrhm_-J-jolJkLm0WDsEouasrXkqpGO_SlWKOJNfRagIu3MWRVm1VGXEnlBvqD324YnDjtUrkBRyoQo"))
                .putData("score", "2.3").putData("time", "3:20").build();
        Integer failCount = manager.send(message);
        Assertions.assertEquals(0, failCount);
    }

    @Test
    public void sendNotiByTopic() throws FailException {
        Message message = Message.builder().setTopic("weather").setNotification(new Notification("title-test-topic", "body-test-topic")).build();
        String response = manager.send(message);
        System.out.println(response);
    }
}
