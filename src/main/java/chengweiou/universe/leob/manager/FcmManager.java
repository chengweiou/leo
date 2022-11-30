package chengweiou.universe.leob.manager;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;

import chengweiou.universe.blackhole.exception.FailException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FcmManager {
    public String send(Message e) throws FailException {
        try {
            return FirebaseMessaging.getInstance().sendAsync(e).get();
        } catch (InterruptedException | ExecutionException ex) {
            log.error("firebase send fail", ex);
            throw new FailException();

        }
    }

    public Integer send(MulticastMessage e) throws FailException {
        try {
            return FirebaseMessaging.getInstance().sendMulticastAsync(e).get().getSuccessCount();
        } catch (InterruptedException | ExecutionException ex) {
            log.error("firebase send fail", ex);
            throw new FailException();

        }
    }
}
