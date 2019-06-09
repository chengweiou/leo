package chengweiou.universe.leonids.service.device;


import chengweiou.universe.leonids.dao.DeviceDao;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceDio {
    @Autowired
    private DeviceDao dao;

    public int save(Device e) {
        e.fillNotRequire();
        e.updateAt();
        return dao.save(e);
    }

    public int delete(Device e) {
        return dao.delete(e);
    }

    public int count(SearchCondition searchCondition, Person person) {
        return dao.countByPerson(searchCondition, person);
    }

    public List<Device> find(SearchCondition searchCondition, Person person) {
        searchCondition.setDefaultSort("updateAt");
        return dao.findByPerson(searchCondition, person);
    }
}
