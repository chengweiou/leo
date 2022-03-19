package chengweiou.universe.leob.controller.me;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.service.notify.NotifyDio;

@RestController
@RequestMapping("me")
public class NotifyControllerMe {
    @Autowired
    private NotifyDio dio;

    @GetMapping("/notify/key")
    public Rest<Notify> findByKey(@RequestHeader("loginAccount") Account loginAccount, Notify e) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        Valid.check("notify.type", e.getType()).isNotNull();
        e.setPerson(loginAccount.getPerson());
        Notify indb = dio.findByKey(e);
        return Rest.ok(indb);
    }

    @GetMapping("/notify/count")
    public Rest<Long> count(@RequestHeader("loginAccount") Account loginAccount, SearchCondition searchCondition, Notify sample) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        sample.setPerson(loginAccount.getPerson());
        long count = dio.count(searchCondition, sample);
        return Rest.ok(count);
    }

    @GetMapping("/notify")
    public Rest<List<Notify>> find(@RequestHeader("loginAccount") Account loginAccount, SearchCondition searchCondition, Notify sample) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        sample.setPerson(loginAccount.getPerson());
        List<Notify> list = dio.find(searchCondition, sample);
        return Rest.ok(list);
    }
}
