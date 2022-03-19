package chengweiou.universe.leob.controller.mg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chengweiou.universe.blackhole.exception.ParamException;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.param.Valid;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.service.notify.NotifyDio;

@RestController
@RequestMapping("mg")
public class NotifyControllerMg {
    @Autowired
    private NotifyDio dio;

    @GetMapping("/notify/key")
    public Rest<Notify> findByKey(Notify e) throws ParamException {
        Valid.check("notify.person", e.getPerson()).isNotNull();
        Valid.check("notify.person.id", e.getPerson().getId()).is().positive();
        Valid.check("notify.type", e.getType()).isNotNull();
        Notify indb = dio.findByKey(e);
        return Rest.ok(indb);
    }

    @GetMapping("/notify/count")
    public Rest<Long> count(SearchCondition searchCondition, Notify sample) throws ParamException {
        Valid.check("notify.person", sample.getPerson()).isNotNull();
        Valid.check("notify.person.id", sample.getPerson().getId()).is().positive();
        long count = dio.count(searchCondition, sample);
        return Rest.ok(count);
    }

    @GetMapping("/notify")
    public Rest<List<Notify>> find(SearchCondition searchCondition, Notify sample) throws ParamException {
        Valid.check("notify.person", sample.getPerson()).isNotNull();
        Valid.check("notify.person.id", sample.getPerson().getId()).is().positive();
        List<Notify> list = dio.find(searchCondition, sample);
        return Rest.ok(list);
    }
}
