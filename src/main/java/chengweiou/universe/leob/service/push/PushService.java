package chengweiou.universe.leob.service.push;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.leob.base.config.ProjConfig;
import chengweiou.universe.leob.manager.FcmManager;
import chengweiou.universe.leob.model.Push;
import chengweiou.universe.leob.model.PushInApp;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Notify;
import chengweiou.universe.leob.model.entity.PushSpec;
import chengweiou.universe.leob.service.notify.NotifyDio;
import chengweiou.universe.leob.service.pushSpec.PushSpecDio;

@Service
public class PushService {
    @Autowired
    private FcmManager fcmManager;
    @Autowired
    private NotifyDio notifyDio;
    @Autowired
    private PushSpecDio pushSpecDio;
    @Autowired
    private ProjConfig config;

    // todo 吧controller的移动到这里来
    // public long push(Push e) throws FailException, ProjException {
    //     // 确认这个项目用户是不是开启推送
    //     PushSpec pushSpecIndb = pushSpecDio.findByKey(e.toPushSpec());
    //     if (pushSpecIndb.notNull() && !pushSpecIndb.getActive()) return 0;
    //     int num = 0;
    //     // 如果推送数字，则获取该用户的最新总数字
    //     if (config.getPushNum()) {
    //         pushSpecIndb.setNum(e.toPushSpec().getNum());
    //         pushSpecService.saveOrUpdateNum(e.toPushSpec());
    //         num = pushSpecService.sumNum(e.toPushSpec());
    //     }

    //     Notify notify = notifyDio.findByKey(Builder.set("person", e.getPerson()).to(new Notify()));
    //     if (!notify.notNull()) return 0;
    //     var a = List.of(notify.getPhoneToken(), notify.getPadToken()).stream().filter(each -> !each.isEmpty()).toList();
    //     long successCount = fcmManager.send(MulticastMessage.builder().addAllTokens(List.of(notify.getPhoneToken(), notify.getPadToken()).stream().filter(each -> !each.isEmpty()).toList())
    //         .putData("pushInApp", e.getPushInApp()!=null ? e.getPushInApp().name() : PushInApp.NONE.name())
    //         .setNotification(Notification.builder().setTitle(e.getName()).setBody(e.getContent()).build())
    //         .setApnsConfig(ApnsConfig.builder().setAps(Aps.builder().setBadge(num).build()).build()
    //     ).build());
    //     // todo me哪边添加一个pushBadge
    //     return successCount;
    // }
}
