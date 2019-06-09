package chengweiou.universe.leonids.service.device;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDio dio;

    @Override
    public void save(Device e) throws FailException {
        int count = dio.save(e);
        if (count != 1) throw new FailException();
    }

    @Override
    public void delete(Device e) throws FailException {
        int count = dio.delete(e);
        if (count != 1) throw new FailException();
    }

    @Override
    public int count(SearchCondition searchCondition, Person person) {
        return dio.count(searchCondition, person);
    }

    @Override
    public List<Device> find(SearchCondition searchCondition, Person person) {
        return dio.find(searchCondition, person);
    }
}
