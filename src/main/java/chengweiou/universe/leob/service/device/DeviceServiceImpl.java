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
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDio dio;

    @Override
    public void save(Device e) throws FailException, ProjException {
        dio.save(e);
    }

    @Override
    public void delete(Device e) throws FailException {
        dio.delete(e);
    }

    @Override
    public long update(Device e) throws ProjException {
        return dio.update(e);
    }

    @Override
    public void saveOrUpdate(Device e) throws FailException, ProjException {
        Device indb = dio.findByKey(e);
        if ( ! indb.notNull()) {
            dio.save(e);
        } else {
            dio.update(Builder.set("id", indb.getId()).to(e));
        }
    }

    @Override
    public Device findById(Device e) {
        return dio.findById(e);
    }

    @Override
    public long count(SearchCondition searchCondition, Device sample) {
        return dio.count(searchCondition, sample);
    }

    @Override
    public List<Device> find(SearchCondition searchCondition, Device sample) {
        return dio.find(searchCondition, sample);
    }
}
