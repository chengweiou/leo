package chengweiou.universe.leo.dao;

import chengweiou.universe.leo.model.SearchCondition;
import chengweiou.universe.leo.model.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDao {
    @Insert("insert into device(personId, token, createAt, updateAt) values(#{person.id}, #{token}, #{createAt}, #{updateAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Device e);

    @Delete("delete from device where id=#{id}")
    int delete(Device e);

    @UpdateProvider(type = Sql.class, method = "update")
    long update(Device e);

    @Select("select * from device where id=#{id}")
    @Results({
            @Result(property = "person.id", column = "personId"),
    })
    Device findById(Device e);
    @Select("select count(*) from device where personId=#{person.id}")
    long countByKey(Device e);
    @Select("select * from device where personId=#{person.id}")
    @Results({
            @Result(property = "person.id", column = "personId"),
    })
    Device findByKey(Device e);

    @SelectProvider(type = Sql.class, method = "count")
    long count(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Device sample);

    @SelectProvider(type = Sql.class, method = "find")
    @Results({@Result(property = "person.id", column = "personId")})
    List<Device> find(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Device sample);

    class Sql {
        public String update(final Device e) {
            return new SQL() {{
                UPDATE("device");
                if (e.getPerson() != null) SET("personId = #{person.id}");
                if (e.getToken() != null) SET("token = #{token}");
                SET("updateAt = #{updateAt}");
                WHERE("id=#{id}");
            }}.toString();
        }
        public String count(@Param("searchCondition")final SearchCondition searchCondition, @Param("sample")final Device sample) {
            return new SQL() {{
                SELECT("count(*)"); FROM("device");
                if (sample != null) {
                    if (sample.getPerson() != null) WHERE("personId = #{sample.person.id}");
                }
            }}.toString();
        }

        public String find(@Param("searchCondition")final SearchCondition searchCondition, @Param("sample")final Device sample) {
            return new SQL() {{
                SELECT("*"); FROM("device");
                if (sample != null) {
                    if (sample.getPerson() != null) WHERE("personId = #{sample.person.id}");
                }
            }}.toString().concat(searchCondition.getOrderBy()).concat(searchCondition.getSqlLimit());
        }
    }
}
