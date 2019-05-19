package chengweiou.universe.leonids.model.entity;

import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.leonids.model.Person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Device implements NotNullObj, Serializable {
    private Long id;
    private Person person;
    private String token;
    private LocalDateTime updateAt;
    public void fillNotRequire() {
    }
    public void updateAt() {
        updateAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public static final Device NULL = new Null();
    public static class Null extends Device implements NullObj {
        @Override
        public Person getPerson() { return Person.NULL; }
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", person=" + person +
                ", token='" + token + '\'' +
                ", updateAt=" + updateAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
