package chengweiou.universe.leonids.service;

import chengweiou.universe.blackhole.model.SearchCondition;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.entity.Device;

import java.util.List;

public interface DeviceService {
    void save(Device e);
    void delete(Device e);

    int count(SearchCondition searchCondition, Person person);
    List<Device> find(SearchCondition searchCondition, Person person);
}
