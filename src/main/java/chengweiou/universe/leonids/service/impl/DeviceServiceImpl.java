package chengweiou.universe.leonids.service.impl;

import chengweiou.universe.blackhole.model.SearchCondition;
import chengweiou.universe.leonids.dao.DeviceDao;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.entity.Device;
import chengweiou.universe.leonids.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao dao;

    @Override
    public void save(Device e) {
        e.fillNotRequire();
        e.updateAt();
        dao.save(e);
    }

    @Override
    public void delete(Device e) {
        dao.delete(e);
    }

    @Override
    public int count(SearchCondition searchCondition, Person person) {
        return dao.count(searchCondition, person);
    }

    @Override
    public List<Device> find(SearchCondition searchCondition, Person person) {
        return dao.find(searchCondition, person);
    }
}
