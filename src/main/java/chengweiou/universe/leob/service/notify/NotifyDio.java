package chengweiou.universe.leob.service.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.dao.BaseDio;
import chengweiou.universe.blackhole.dao.BaseSQL;
import chengweiou.universe.blackhole.model.AbstractSearchCondition;
import chengweiou.universe.leob.dao.NotifyDao;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.model.entity.notify.Notify.Dto;

@Component
public class NotifyDio extends BaseDio<Notify, Dto> {
    @Autowired
    private NotifyDao dao;
    @Override
    protected NotifyDao getDao() { return dao; }
    @Override
    protected String baseFind(AbstractSearchCondition searchCondition, Dto sample) {
        return new BaseSQL() {{
            if (searchCondition.getIdList() != null) WHERE("id in ${searchCondition.foreachIdList}");
            if (sample != null) {
                if (sample.getPersonId() != null) WHERE("personId = #{sample.personId}");
                if (sample.getType() != null) WHERE("type = #{sample.type}");
                if (sample.getActive() != null) WHERE("active = #{sample.active}");
            }
        }}.toString();
    }

}
