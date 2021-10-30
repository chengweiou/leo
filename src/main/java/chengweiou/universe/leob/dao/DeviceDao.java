package chengweiou.universe.leob.dao;

import chengweiou.universe.leob.base.dao.BaseDao;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device.Dto;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDao extends BaseDao<Dto> {

    class Sql {

    }

}
