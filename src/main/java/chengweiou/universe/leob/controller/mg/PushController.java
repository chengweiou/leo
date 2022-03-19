package chengweiou.universe.leob.controller.mg;

import java.util.List;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.base.config.ProjConfig;
import chengweiou.universe.leob.manager.FcmManager;
import chengweiou.universe.leob.model.Push;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.service.device.DeviceDio;
import chengweiou.universe.leob.service.notify.NotifyDio;
import chengweiou.universe.leob.service.notify.NotifyService;

@RestController
@RequestMapping("mg")
public class PushController {
    @Autowired
    private FcmManager fcmManager;
    @Autowired
    private DeviceDio deviceDio;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private NotifyDio notifyDio;
    @Autowired
    private ProjConfig config;
    @PostMapping("/push")
    public Rest<Long> push(Push e) throws ParamException, FailException, ProjException {
        Valid.check("push.person", e.getPerson()).isNotNull();
        Valid.check("push.person.id", e.getPerson().getId()).is().positive();
        Valid.check("push.name", e.getName()).is().lengthIn(500);
        Valid.check("push.content", e.getContent()).is().lengthIn(500);
        if (e.getNotifyType() != null) Valid.check("push.notifyType", e.getNotifyType()).is().of(config.getNotifyTypeList());

        // 确认这个项目用户是不是开启推送
        Notify notifyIndb = notifyDio.findByKey(e.toNotify());
        if (notifyIndb.notNull() && !notifyIndb.getActive()) return Rest.ok(0);
        int num = 0;
        // 如果推送数字，则获取该用户的最新总数字
        if (config.getPushNum()) {
            notifyIndb.setNum(e.toNotify().getNum());
            notifyService.saveOrUpdateNum(e.toNotify());
            num = notifyService.sumNum(e.toNotify());
        }
        Device device = deviceDio.findByKey(Builder.set("person", e.getPerson()).to(new Device()));
        if (!device.notNull()) return Rest.ok(0);
        long successCount = fcmManager.send(MulticastMessage.builder().addToken(device.getToken())
            .setNotification(Notification.builder().setTitle(e.getName()).setBody(e.getContent()).build())
            .setApnsConfig(ApnsConfig.builder().setAps(Aps.builder().setBadge(num).build())
            .build()
        ).build());
        // todo me哪边添加一个pushBadge
        return Rest.ok(successCount);
    }

    @PostMapping("/push/topic")
    public Rest<Long> pushTopic(Push e) throws ParamException, FailException {
        Valid.check("push.topic", e.getTopic()).is().lengthIn(500);
        Valid.check("push.name", e.getName()).is().lengthIn(500);
        Valid.check("push.content", e.getContent()).is().lengthIn(500);
//        todo setcondition 多个同时订阅才有效（交钱+指定主题）
//        todo 多个同样topic 不收到相同推送
// todo active 在这里怎么处理
        fcmManager.send(Message.builder().setTopic(e.getTopic())
                .setNotification(Notification.builder().setTitle(e.getName()).setBody(e.getContent()).build())

                .build());
        return Rest.ok(true);
    }
}
