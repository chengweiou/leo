package chengweiou.universe.leob.controller.all;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.service.device.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService service;
    @PostMapping("/device")
    public Rest<Long> save(Device e, @RequestHeader("loginAccount") Account loginAccount) throws ParamException, FailException, ProjException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        Valid.check("device.token", e.getToken()).is().lengthIn(500);
        e.setPerson(loginAccount.getPerson());
        // todo 判断来源设备种类，决定是save 还是saveorupdate
        service.saveOrUpdate(e);
        return Rest.ok(e.getId());
    }
}
