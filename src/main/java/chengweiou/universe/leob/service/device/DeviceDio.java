package chengweiou.universe.leob.service.device;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.leob.dao.DeviceDao;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;

@Component
public class DeviceDio {
    @Autowired
    private DeviceDao dao;

    public void save(Device e) throws FailException, ProjException {
        long count = dao.countByKey(e.toDto());
        if (count != 0) throw new ProjException("dup key: person:" + e.getPerson().getId() + " exists", BasicRestCode.EXISTS);
        e.fillNotRequire();
        e.createAt();
        e.updateAt();
        Device.Dto dto = e.toDto();
        count = dao.save(dto);
        if (count != 1) throw new FailException();
        e.setId(dto.getId());
    }

    public void delete(Device e) throws FailException {
        long count = dao.delete(e.toDto());
        if (count != 1) throw new FailException();
    }

    public long update(Device e) {
        e.updateAt();
        return dao.update(e.toDto());
    }

    public Device findById(Device e) {
        Device.Dto result = dao.findById(e.toDto());
        if (result == null) return Device.NULL;
        return result.toBean();
    }

    public long countByKey(Device e) {
        return dao.countByKey(e.toDto());
    }
    public Device findByKey(Device e) {
        Device.Dto result = dao.findByKey(e.toDto());
        if (result == null) return Device.NULL;
        return result.toBean();
    }

    public long count(SearchCondition searchCondition, Device sample) {
        return dao.count(searchCondition, sample!=null ? sample.toDto() : null);
    }

    public List<Device> find(SearchCondition searchCondition, Device sample) {
        searchCondition.setDefaultSort("updateAt");
        List<Device.Dto> dtoList = dao.find(searchCondition, sample!=null ? sample.toDto() : null);
        List<Device> result = dtoList.stream().map(e -> e.toBean()).collect(Collectors.toList());
        return result;
    }

}
