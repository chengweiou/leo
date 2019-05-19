package chengweiou.universe.leonids.controller.rest;

import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leonids.init.converter.Account;
import chengweiou.universe.leonids.model.entity.Device;
import chengweiou.universe.leonids.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService service;
    @PostMapping("/device")
    public Rest<Long> save(Device e, @RequestHeader("loginAccount") Account loginAccount) throws ParamException {
        Valid.check("loginAccount.person", loginAccount.getPerson()).isNotNull();
        Valid.check("loginAccount.person.id", loginAccount.getPerson().getId()).is().positive();
        Valid.check("device.token", e.getToken()).is().lengthIn(500);
        e.setPerson(loginAccount.getPerson());
        service.save(e);
        return Rest.ok(e.getId());
    }
}
