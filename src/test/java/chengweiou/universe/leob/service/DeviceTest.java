package chengweiou.universe.leob.service;


import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.data.Data;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Device;
import chengweiou.universe.leob.service.device.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    private Data data;

    @Test
    public void saveDelete() throws FailException, ProjException {
        Device e = Builder.set("person", Builder.set("id", 21L).to(new Person()))
                .set("token", "asdf")
                .to(new Device());
        service.save(e);
        Assertions.assertEquals(true, e.getId() > 0);
        service.delete(e);
    }

    @Test
    public void update() throws ProjException {
        String old = data.deviceList.get(0).getToken();
        Device e = Builder.set("id", data.deviceList.get(0).getId()).set("token", "aaa").to(new Device());
        long count = service.update(e);
        Assertions.assertEquals(1, count);
        Device indb = service.findById(e);
        Assertions.assertEquals("aaa", indb.getToken());
        Builder.set("token", old).to(e);
        service.update(e);
    }

    @Test
    public void saveOrUpdate() throws FailException, ProjException {
        Device e1 = Builder.set("person", Builder.set("id", 20).to(new Person())).set("token", "aaa").to(new Device());
        service.saveOrUpdate(e1);
        Device e2 = Builder.set("person", Builder.set("id", 20).to(new Person())).set("token", "bbb").to(new Device());
        service.saveOrUpdate(e2);
        Assertions.assertEquals(true, e1.getId()> 0);
        Assertions.assertEquals(e2.getId(), e1.getId());
        service.delete(e2);
    }

    @Test
    public void count() {
        long count = service.count(new SearchCondition(), Builder.set("person", Builder.set("id", 1L).to(new Person())).to(new Device()));
        Assertions.assertEquals(1, count);
    }

    @Test
    public void find() {
        List<Device> list = service.find(new SearchCondition(), Builder.set("person", Builder.set("id", 1L).to(new Person())).to(new Device()));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1L, list.get(0).getPerson().getId());
    }
    @BeforeEach
    public void init() {
        data.init();
    }

}
