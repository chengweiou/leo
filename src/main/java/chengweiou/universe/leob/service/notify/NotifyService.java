package chengweiou.universe.leob.service.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.entity.Notify;

@Service
public class NotifyService {
    @Autowired
    private NotifyDio dio;

    public void saveOrUpdate(Notify e) throws FailException, ProjException {
        Notify indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else {
            dio.update(Builder.set("id", indb.getId()).to(e));
        }
    }

}
