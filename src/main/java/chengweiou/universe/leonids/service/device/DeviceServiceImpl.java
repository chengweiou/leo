package chengweiou.universe.leonids.service.device;

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
    public void save(Device e) {
        dio.save(e);
    }

    @Override
    public void delete(Device e) {
        dio.delete(e);
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
