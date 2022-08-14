package chengweiou.universe.leob.service;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.data.Data;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.Notify;
import chengweiou.universe.leob.service.notify.NotifyDio;
import chengweiou.universe.leob.service.notify.NotifyService;

@SpringBootTest
@ActiveProfiles("test")
public class NotifyTest {
    @Autowired
    private NotifyService service;
    @Autowired
    private NotifyDio dio;
    @Autowired
    private Data data;

    @Test
    public void saveDelete() throws FailException, ProjException {
        Notify e = Builder.set("person", Builder.set("id", 21L).to(new Person()))
                .set("phoneToken", "asdf")
                .to(new Notify());
        dio.save(e);
        Assertions.assertEquals(true, e.getId() > 0);
        dio.delete(e);
    }

    @Test
    public void update() throws ProjException {
        String old = data.notifyList.get(0).getPhoneToken();
        Notify e = Builder.set("id", data.notifyList.get(0).getId()).set("phoneToken", "aaa").to(new Notify());
        long count = dio.update(e);
        Assertions.assertEquals(1, count);
        Notify indb = dio.findById(e);
        Assertions.assertEquals("aaa", indb.getPhoneToken());
        Builder.set("phoneToken", old).to(e);
        dio.update(e);
    }

    @Test
    public void saveOrUpdate() throws FailException, ProjException {
        Notify e1 = Builder.set("person", Builder.set("id", 20).to(new Person())).set("phoneToken", "aaa").to(new Notify());
        service.saveOrUpdate(e1);
        Notify e2 = Builder.set("person", Builder.set("id", 20).to(new Person())).set("phoneToken", "bbb").to(new Notify());
        service.saveOrUpdate(e2);
        Assertions.assertEquals(true, e1.getId()> 0);
        Assertions.assertEquals(e2.getId(), e1.getId());
        dio.delete(e2);
    }

    @Test
    public void count() {
        long count = dio.count(new SearchCondition(), Builder.set("person", Builder.set("id", 1L).to(new Person())).to(new Notify()));
        Assertions.assertEquals(1, count);
    }

    @Test
    public void find() {
        List<Notify> list = dio.find(new SearchCondition(), Builder.set("person", Builder.set("id", 1L).to(new Person())).to(new Notify()));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1L, list.get(0).getPerson().getId());
    }
    @BeforeEach
    public void init() {
        data.init();
    }

}
