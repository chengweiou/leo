package chengweiou.universe.leob.service.pushSpec;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.PushSpec;

@Service
public class PushSpecService {
    @Autowired
    private PushSpecDio dio;

    public void saveOrUpdateNum(PushSpec e) throws FailException, ProjException {
        PushSpec indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else if (e.getNum() != null) {
            dio.update(Builder.set("num", e.getNum()).to(indb));
        }
    }

    public int sumNum(PushSpec e) {
        List<PushSpec> list = dio.find(Builder.set("limit", 0).to(new SearchCondition()), Builder.set("person", e.getPerson()).set("active", true).to(new PushSpec()));
        return list.stream().mapToInt(each-> each.getNum()).sum();
    }
}
