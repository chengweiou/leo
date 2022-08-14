package chengweiou.universe.leob.model;

import java.io.Serializable;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.leob.model.entity.PushSpec;
import lombok.Data;

@Data
public class Push implements NotNullObj, Serializable {
    private Person person;
    private String topic;
    private String name;
    private String content;
    private String pushSpecType;
    private Integer num;
    private PushInApp pushInApp; // 给前端使用
    public static final Push NULL = new Push.Null();
    public static class Null extends Push implements NullObj {
    }

    private PushSpec pushSpec;
    public PushSpec toPushSpec() {
        if (pushSpec == null) pushSpec = Builder
            .set("person", person)
            .set("type", (pushSpecType!=null ? pushSpecType : "none"))
            .set("num", num)
            .to(new PushSpec());
        return pushSpec;
    }
}
