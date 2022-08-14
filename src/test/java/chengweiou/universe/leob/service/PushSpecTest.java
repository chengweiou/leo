package chengweiou.universe.leob.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.exception.ProjException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.data.Data;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.SearchCondition;
import chengweiou.universe.leob.model.entity.PushSpec;
import chengweiou.universe.leob.service.pushSpec.PushSpecDio;
import chengweiou.universe.leob.service.pushSpec.PushSpecService;

@SpringBootTest
@ActiveProfiles("test")
public class PushSpecTest {
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private Account loginAccount;

    @Autowired
	private Data data;

    @Autowired
    private PushSpecDio dio;
	@Autowired
    private PushSpecService service;

    @Test
	public void saveDelete() throws FailException {
		PushSpec e = Builder.set("person", data.personList.get(7)).set("type", "chat").to(new PushSpec());
		dio.save(e);
		Assertions.assertEquals(true, e.getId()> 0);
		dio.delete(e);
	}

	@Test
	public void update() {
		PushSpec e = Builder.set("id", data.pushSpecList.get(0).getId()).set("active", false).to(new PushSpec());
		long count = dio.update(e);
		Assertions.assertEquals(1, count);
		PushSpec indb = dio.findById(e);
		Assertions.assertEquals(false, indb.getActive());

		dio.update(data.pushSpecList.get(0));
	}

	@Test
	public void count() {
		long count = dio.count(new SearchCondition(), null);
		Assertions.assertEquals(2, count);
	}

	@Test
	public void find() {
		PushSpec sample = Builder.set("person", data.personList.get(0)).to(new PushSpec());
		List<PushSpec> list = dio.find(new SearchCondition(), sample);
		Assertions.assertEquals(2, list.size());
	}

	@Test
	public void saveOrUpdateNum() throws FailException, ProjException {
		service.saveOrUpdateNum(Builder.set("person", data.pushSpecList.get(1).getPerson()).set("type", data.pushSpecList.get(1).getType()).set("num", 10).to(new PushSpec()));
		PushSpec indb = dio.findByKey(data.pushSpecList.get(1));
		Assertions.assertEquals(10, indb.getNum());
		dio.update(data.pushSpecList.get(1));

		service.saveOrUpdateNum(Builder.set("person", Builder.set("id", 7).to(new Person())).set("num", 12).to(new PushSpec()));
		indb = dio.findByKey(Builder.set("person", Builder.set("id", 7).to(new Person())).set("type", "none").to(new PushSpec()));
		Assertions.assertEquals(12, indb.getNum());
		dio.delete(indb);

	}

	@Test
	public void sumNum() {
		long num = service.sumNum(data.pushSpecList.get(0));
		Assertions.assertEquals(2, num);
	}

	@BeforeEach
	public void before() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		loginAccount = Builder.set("person", Builder.set("id", 1L).to(new Person()))
				.set("extra", "SUPER")
				.to(new Account());
	}
	@BeforeEach
	public void init() {
		data.init();
	}
}
