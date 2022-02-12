package chengweiou.universe.leob.service.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.entity.Device;

@Service
public class DeviceService {
    @Autowired
    private DeviceDio dio;

    public void saveOrUpdate(Device e) throws FailException, ProjException {
        Device indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else {
            dio.update(Builder.set("id", indb.getId()).to(e));
        }
    }

}
