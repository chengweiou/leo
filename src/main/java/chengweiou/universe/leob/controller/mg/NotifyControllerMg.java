package chengweiou.universe.leob.controller.mg;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.model.entity.Notify;
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
    private NotifyService service;
    @PostMapping("/notify")
    public Rest<Long> save(Notify e, @RequestHeader("loginAccount") Account loginAccount) throws ParamException, FailException, ProjException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        e.setPerson(loginAccount.getPerson());
        // todo 判断来源设备种类，决定是save 还是saveorupdate
        service.saveOrUpdate(e);
        // todo 这里是不是也吧所有的 notify 也自动加上，要用saveOrUpdate，这个可能被用户多次请求
        return Rest.ok(e.getId());
    }
}
