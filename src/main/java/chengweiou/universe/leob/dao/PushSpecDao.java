package chengweiou.universe.leob.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import chengweiou.universe.leob.base.dao.BaseDao;
import chengweiou.universe.leob.model.entity.PushSpec.Dto;

@Repository
@Mapper
public interface PushSpecDao extends BaseDao<Dto> {

    class Sql {

    }

}
