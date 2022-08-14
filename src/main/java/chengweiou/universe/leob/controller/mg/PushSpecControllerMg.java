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
import chengweiou.universe.leob.model.entity.PushSpec;
import chengweiou.universe.leob.service.pushSpec.PushSpecDio;

@RestController
@RequestMapping("mg")
public class PushSpecControllerMg {
    @Autowired
    private PushSpecDio dio;

    @GetMapping("/pushSpec/key")
    public Rest<PushSpec> findByKey(PushSpec e) throws ParamException {
        Valid.check("pushSpec.person", e.getPerson()).isNotNull();
        Valid.check("pushSpec.person.id", e.getPerson().getId()).is().positive();
        Valid.check("pushSpec.type", e.getType()).isNotNull();
        PushSpec indb = dio.findByKey(e);
        return Rest.ok(indb);
    }

    @GetMapping("/pushSpec/count")
    public Rest<Long> count(SearchCondition searchCondition, PushSpec sample) throws ParamException {
        Valid.check("pushSpec.person", sample.getPerson()).isNotNull();
        Valid.check("pushSpec.person.id", sample.getPerson().getId()).is().positive();
        long count = dio.count(searchCondition, sample);
        return Rest.ok(count);
    }

    @GetMapping("/pushSpec")
    public Rest<List<PushSpec>> find(SearchCondition searchCondition, PushSpec sample) throws ParamException {
        Valid.check("pushSpec.person", sample.getPerson()).isNotNull();
        Valid.check("pushSpec.person.id", sample.getPerson().getId()).is().positive();
        List<PushSpec> list = dio.find(searchCondition, sample);
        return Rest.ok(list);
    }
}
