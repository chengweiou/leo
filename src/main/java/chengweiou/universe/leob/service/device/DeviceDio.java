package chengweiou.universe.leob.service.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.dao.BaseDio;
import chengweiou.universe.blackhole.dao.BaseSQL;
import chengweiou.universe.blackhole.model.AbstractSearchCondition;
import chengweiou.universe.leob.dao.DeviceDao;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.model.entity.Device.Dto;

@Component
public class DeviceDio extends BaseDio<Device, Device.Dto> {
    @Autowired
    private DeviceDao dao;
    @Override
    protected DeviceDao getDao() { return dao; }
    @Override
    protected String baseFind(AbstractSearchCondition searchCondition, Dto sample) {
        return new BaseSQL() {{
            if (searchCondition.getIdList() != null) WHERE("id in ${searchCondition.foreachIdList}");
            if (sample != null) {
                if (sample.getPersonId() != null) WHERE("personId = #{sample.personId}");
            }
        }}.toString();
    }

}
