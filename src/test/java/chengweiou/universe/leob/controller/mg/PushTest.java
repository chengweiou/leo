package chengweiou.universe.leob.controller.mg;


import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.util.GsonUtil;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.data.Data;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.entity.notify.Notify;
import chengweiou.universe.leob.service.notify.NotifyDio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
public class PushTest {
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private Account loginAccount;
	@Autowired
	private Data data;

	@Autowired
    private NotifyDio notifyDio;
	@Test
	public void push() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/mg/push")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
				.param("person.id", "1")
				.param("name", "controller-test-title")
				.param("content", "controller-test-body-person")
				.param("num", "11")
			).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.OK, saveRest.getCode());
		Notify indb = notifyDio.findById(data.notifyList.get(0));
		Assertions.assertEquals(11, indb.getNum());
		notifyDio.update(data.notifyList.get(0));
	}

	@Test
	public void pushToNewPerson() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/mg/push")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
				.param("person.id", "31")
				.param("name", "controller-test-title")
				.param("content", "controller-test-body-person")
				.param("num", "31")
			).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.OK, saveRest.getCode());
		Notify indb = notifyDio.findByKey(Builder.set("person", Builder.set("id", 31).to(new Person())).set("type", "none").to(new Notify()));
		Assertions.assertEquals(31, indb.getNum());
		notifyDio.delete(indb);
	}

	@Test
	public void pushTop() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/mg/push/topic")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
				.param("topic", "weather")
				.param("name", "controller-test-title")
				.param("content", "controller-test-body-weather")
		).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.OK, saveRest.getCode());
	}
	@BeforeEach
	public void before() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		loginAccount = Builder.set("person", Builder.set("id", 10L).to(new Person()))
				.set("extra", "SUPER")
				.to(new Account());
	}
	@BeforeEach
	public void init() {
		data.init();
	}
}
