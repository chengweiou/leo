package chengweiou.universe.leob.device;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.model.SearchCondition;

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
