package chengweiou.universe.leonids.service.device;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.leonids.model.SearchCondition;
import chengweiou.universe.leonids.model.entity.Device;

import java.util.List;

public interface DeviceService {
    void save(Device e) throws FailException, ProjException;
    void delete(Device e) throws FailException;

    long update(Device e) throws ProjException;
    void saveOrUpdate(Device e) throws FailException, ProjException;

    Device findById(Device e);

    long count(SearchCondition searchCondition, Device sample);
    List<Device> find(SearchCondition searchCondition, Device sample);
}
