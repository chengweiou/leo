package chengweiou.universe.leob.service.device;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDio dio;

    public void save(Device e) throws FailException, ProjException {
        dio.save(e);
    }

    public void delete(Device e) throws FailException {
        dio.delete(e);
    }

    public long update(Device e) throws ProjException {
        return dio.update(e);
    }

    public void saveOrUpdate(Device e) throws FailException, ProjException {
        Device indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else {
            dio.update(Builder.set("id", indb.getId()).to(e));
        }
    }

    public Device findById(Device e) {
        return dio.findById(e);
    }

    public long count(SearchCondition searchCondition, Device sample) {
        return dio.count(searchCondition, sample);
    }

    public List<Device> find(SearchCondition searchCondition, Device sample) {
        return dio.find(searchCondition, sample);
    }
}
