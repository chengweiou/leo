package chengweiou.universe.leob.controller.mg;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.model.entity.Notify;
import chengweiou.universe.leob.service.notify.NotifyDio;
import chengweiou.universe.leob.service.notify.NotifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mg")
public class NotifyControllerMg {
    @Autowired
    private NotifyDio dio;
    @Autowired
    private NotifyService service;
    @PostMapping("/notify")
    public Rest<Long> save(Notify e) throws ParamException, FailException, ProjException {
        Valid.check("person", e.getPerson()).isNotNull();
        Valid.check("person.id", e.getPerson().getId()).is().positive();
        dio.saveOrUpdateByKey(e);
        return Rest.ok(e.getId());
    }
}
