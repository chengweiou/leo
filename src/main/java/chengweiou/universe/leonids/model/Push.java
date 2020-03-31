package chengweiou.universe.leonids.model;

import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import lombok.Data;

import java.io.Serializable;

@Data
public class Push implements NotNullObj, Serializable {
    private Person person;
    private String topic;
    private String name;
    private String content;
    public static final Push NULL = new Push.Null();
    public static class Null extends Push implements NullObj {
    }
}
