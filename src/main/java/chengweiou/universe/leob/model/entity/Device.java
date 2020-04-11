package chengweiou.universe.leob.model.entity;

import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.leob.model.Person;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class Device implements NotNullObj, Serializable {
    private Long id;
    private Person person;
    private String token;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    public void fillNotRequire() {
    }
    public void createAt() {
        createAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public void updateAt() {
        updateAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public static final Device NULL = new Null();
    public static class Null extends Device implements NullObj {
        @Override
        public Person getPerson() { return Person.NULL; }
    }
}
