package chengweiou.universe.leonids.service.device;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;

import java.util.List;

public interface DeviceService {
    void save(Device e) throws FailException;
    void delete(Device e) throws FailException;

    int count(SearchCondition searchCondition, Person person);
    List<Device> find(SearchCondition searchCondition, Person person);
}
