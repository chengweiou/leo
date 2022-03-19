package chengweiou.universe.leob.manager;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.util.LogUtil;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FcmManager {
    public String send(Message e) throws FailException {
        try {
            return FirebaseMessaging.getInstance().sendAsync(e).get();
        } catch (InterruptedException | ExecutionException ex) {
            LogUtil.e("firebase send fail", ex);
            throw new FailException();

        }
    }

    public Integer send(MulticastMessage e) throws FailException {
        try {
            return FirebaseMessaging.getInstance().sendMulticastAsync(e).get().getSuccessCount();
        } catch (InterruptedException | ExecutionException ex) {
            LogUtil.e("firebase send fail", ex);
            throw new FailException();

        }
    }
}
