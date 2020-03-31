package chengweiou.universe.leonids.controller.api;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leonids.manager.FcmManager;
import chengweiou.universe.leonids.model.Push;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;
import chengweiou.universe.leonids.service.device.DeviceService;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class PushController {
    @Autowired
    private FcmManager fcmManager;
    @Autowired
    private DeviceService deviceService;
    @PostMapping("/push")
    public Rest<Long> push(Push e) throws ParamException, FailException {
        Valid.check("push.person", e.getPerson()).isNotNull();
        Valid.check("push.person.id", e.getPerson().getId()).is().positive();
        Valid.check("push.name", e.getName()).is().lengthIn(500);
        Valid.check("push.content", e.getContent()).is().lengthIn(500);
        List<Device> deviceList = deviceService.find(new SearchCondition(), Builder.set("person", e.getPerson()).to(new Device()));
        List<String> tokenList = deviceList.stream().map(Device::getToken).collect(Collectors.toList());
        fcmManager.send(MulticastMessage.builder().addAllTokens(tokenList).setNotification(new Notification(e.getName(), e.getContent())).build());
        return Rest.ok(true);
    }

    @PostMapping("/push/topic")
    public Rest<Long> pushTopic(Push e) throws ParamException, FailException {
        Valid.check("push.topic", e.getTopic()).is().lengthIn(500);
        Valid.check("push.name", e.getName()).is().lengthIn(500);
        Valid.check("push.content", e.getContent()).is().lengthIn(500);
//        todo setcondition 多个同时订阅才有效（交钱+指定主题）
//        todo 多个同样topic 不收到相同推送
        fcmManager.send(Message.builder().setTopic(e.getTopic()).setNotification(new Notification(e.getName(), e.getContent())).build());
        return Rest.ok(true);
    }
}
