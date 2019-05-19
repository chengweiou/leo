package chengweiou.universe.leonids.service;


import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.SearchCondition;
import chengweiou.universe.leonids.model.Person;
import chengweiou.universe.leonids.model.entity.Device;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DeviceTest {
    @Autowired
    private DeviceService service;

    @Test
    public void saveDelete() {
        Device e = Builder.set("person", Builder.set("id", 10L).to(new Person()))
                .set("token", "asdf")
                .to(new Device());
        service.save(e);
        Assertions.assertEquals(true, e.getId() > 0);
        service.delete(e);
    }

    @Test
    public void count() {
        int count = service.count(new SearchCondition(), Builder.set("id", 1L).to(new Person()));
        Assertions.assertEquals(1, count);
    }

    @Test
    public void find() {
        SearchCondition searchCondition = Builder.set("k", "1").to(new SearchCondition());
        List<Device> list = service.find(searchCondition, Builder.set("id", 1L).to(new Person()));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1L, list.get(0).getPerson().getId());
    }
}
