package chengweiou.universe.leob.model;

import java.io.Serializable;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.leob.model.entity.notify.Notify;
import lombok.Data;

@Data
public class Push implements NotNullObj, Serializable {
    private Person person;
    private String topic;
    private String name;
    private String content;
    private String notifyType;
    private Integer num;
    public static final Push NULL = new Push.Null();
    public static class Null extends Push implements NullObj {
    }

    private Notify notify;
    public Notify toNotify() {
        if (notify == null) notify = Builder
            .set("person", person)
            .set("type", (notifyType!=null ? notifyType : "none"))
            .set("num", num)
            .to(new Notify());
        return notify;
    }
}
