package chengweiou.universe.leob.controller.mg;


import java.util.List;

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

import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.util.GsonUtil;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.data.Data;
import chengweiou.universe.leob.model.Person;
import chengweiou.universe.leob.model.entity.notify.Notify;

@SpringBootTest
@ActiveProfiles("test")
public class NotifyTest {
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private Account loginAccount;
	@Autowired
	private Data data;

	@Test
	public void findById() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.get("/mg/notify/key")
			.header("loginAccount", GsonUtil.create().toJson(loginAccount))
			.param("person.id", "1").param("type", "none")
		).andReturn().getResponse().getContentAsString();
		Rest<Notify> rest = Rest.from(result, Notify.class);
		Assertions.assertEquals(BasicRestCode.OK, rest.getCode(), rest.getMessage());
		Assertions.assertEquals(data.notifyList.get(0).getId(), rest.getData().getId());
	}

	@Test
	public void count() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.get("/mg/notify/count")
			.header("loginAccount", GsonUtil.create().toJson(loginAccount))
			.param("person.id", "1")
		).andReturn().getResponse().getContentAsString();
		Rest<Long> rest = Rest.from(result, Long.class);
		Assertions.assertEquals(BasicRestCode.OK, rest.getCode());
		Assertions.assertEquals(2, rest.getData());
	}

	@Test
	public void find() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.get("/mg/notify")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
				.param("person.id", "1")
		).andReturn().getResponse().getContentAsString();
		Rest<List> rest = Rest.from(result, List.class);
		Assertions.assertEquals(BasicRestCode.OK, rest.getCode());
		Assertions.assertEquals(2, rest.getData().size());
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
