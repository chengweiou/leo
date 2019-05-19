package chengweiou.universe.leonids.dao;

import chengweiou.universe.blackhole.model.SearchCondition;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDao {
    @Insert("insert into device(personId, token, updateAt) values(#{person.id}, #{token}, #{updateAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Device e);

    @Delete("delete from device where id=#{id}")
    int delete(Device e);

    @SelectProvider(type = Sql.class, method = "countByPerson")
    int count(@Param("searchCondition") SearchCondition searchCondition, @Param("person") Person person);

    @SelectProvider(type = Sql.class, method = "findByPerson")
    @Results({@Result(property = "person.id", column = "personId")})
    List<Device> find(@Param("searchCondition") SearchCondition searchCondition, @Param("person") Person person);

    class Sql {
        public String countByPerson(@Param("searchCondition")final SearchCondition searchCondition, @Param("person")final Person person) {
            return new SQL() {{
                SELECT("count(*)"); FROM("device");
                WHERE("personId = #{person.id}");
            }}.toString();
        }

        public String findByPerson(@Param("searchCondition")final SearchCondition searchCondition, @Param("person")final Person person) {
            return new SQL() {{
                SELECT("*"); FROM("device");
                WHERE("personId = #{person.id}");
            }}.toString().concat(searchCondition.getOrderBy()).concat(searchCondition.getSqlLimit());
        }
    }
}
