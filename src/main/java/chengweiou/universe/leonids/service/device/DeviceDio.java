package chengweiou.universe.leonids.service.device;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.leonids.dao.DeviceDao;
import chengweiou.universe.leonids.model.ProjectRestCode;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceDio {
    @Autowired
    private DeviceDao dao;

    public void save(Device e) throws FailException, ProjException {
        long count = dao.countByKey(e);
        if (count != 0) throw new ProjException("dup key: person:" + e.getPerson().getId() + " exists", ProjectRestCode.EXISTS);
        e.fillNotRequire();
        e.createAt();
        e.updateAt();
        count = dao.save(e);
        if (count != 1) throw new FailException();
    }

    public void delete(Device e) throws FailException {
        long count = dao.delete(e);
        if (count != 1) throw new FailException();
    }

    public long update(Device e) throws ProjException {
        e.updateAt();
        return dao.update(e);
    }

    public Device findById(Device e) {
        Device result = dao.findById(e);
        return result != null ? result : Device.NULL;
    }

    public Device findByKey(Device e) {
        Device result = dao.findByKey(e);
        return result != null ? result : Device.NULL;
    }

    public long count(SearchCondition searchCondition, Device sample) {
        return dao.count(searchCondition, sample);
    }

    public List<Device> find(SearchCondition searchCondition, Device sample) {
        searchCondition.setDefaultSort("updateAt");
        return dao.find(searchCondition, sample);
    }
}
