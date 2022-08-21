package chengweiou.universe.leob.model.entity;

import org.springframework.beans.BeanUtils;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.blackhole.model.entity.DtoEntity;
import chengweiou.universe.blackhole.model.entity.DtoKey;
import chengweiou.universe.blackhole.model.entity.ServiceEntity;
import chengweiou.universe.leob.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PushSpec extends ServiceEntity {
    private Person person;
    private String type;
    private Boolean active;
    private Integer num;
    public void fillNotRequire() {
        type = type != null ? type : "none";
        active = active != null ? active : true;
        num = num != null ? num : 0;
    }
    public static final PushSpec NULL = new Null();
    public static class Null extends PushSpec implements NullObj {
        @Override
        public Person getPerson() { return Person.NULL; }
    }
    public Dto toDto() {
        Dto result = new Dto();
        BeanUtils.copyProperties(this, result);
        if (person != null) result.setPersonId(person.getId());
        return result;
    }
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Dto extends DtoEntity {
        @DtoKey(group="key")
        private Long personId;
        @DtoKey(group="key")
        private String type;
        private Boolean active;
        private Integer num;

        public PushSpec toBean() {
            PushSpec result = new PushSpec();
            BeanUtils.copyProperties(this, result);
            result.setPerson(Builder.set("id", personId).to(new Person()));
            return result;
        }
    }
}
