package chengweiou.universe.leob.service.notify;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.notify.Notify;

@Service
public class NotifyService {
    @Autowired
    private NotifyDio dio;

    public void saveOrUpdateNum(Notify e) throws FailException, ProjException {
        Notify indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else if (e.getNum() != null) {
            dio.update(Builder.set("num", e.getNum()).to(indb));
        }
    }

    public int sumNum(Notify e) {
        List<Notify> list = dio.find(Builder.set("limit", 0).to(new SearchCondition()), Builder.set("person", e.getPerson()).set("active", true).to(new Notify()));
        return list.stream().mapToInt(each-> each.getNum()).sum();
    }
}
