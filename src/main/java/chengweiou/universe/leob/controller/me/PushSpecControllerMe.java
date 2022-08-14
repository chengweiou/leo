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
import chengweiou.universe.leob.model.entity.PushSpec;
import chengweiou.universe.leob.service.pushSpec.PushSpecDio;

@RestController
@RequestMapping("me")
public class PushSpecControllerMe {
    @Autowired
    private PushSpecDio dio;

    @GetMapping("/pushSpec/key")
    public Rest<PushSpec> findByKey(@RequestHeader("loginAccount") Account loginAccount, PushSpec e) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        Valid.check("pushSpec.type", e.getType()).isNotNull();
        e.setPerson(loginAccount.getPerson());
        PushSpec indb = dio.findByKey(e);
        return Rest.ok(indb);
    }

    @GetMapping("/pushSpec/count")
    public Rest<Long> count(@RequestHeader("loginAccount") Account loginAccount, SearchCondition searchCondition, PushSpec sample) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        sample.setPerson(loginAccount.getPerson());
        long count = dio.count(searchCondition, sample);
        return Rest.ok(count);
    }

    @GetMapping("/pushSpec")
    public Rest<List<PushSpec>> find(@RequestHeader("loginAccount") Account loginAccount, SearchCondition searchCondition, PushSpec sample) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        sample.setPerson(loginAccount.getPerson());
        List<PushSpec> list = dio.find(searchCondition, sample);
        return Rest.ok(list);
    }
}
