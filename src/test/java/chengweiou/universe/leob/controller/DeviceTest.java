package chengweiou.universe.leob.controller;


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
import chengweiou.universe.leob.model.Person;

@SpringBootTest
@ActiveProfiles("test")
public class DeviceTest {
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private Account loginAccount;

	@Test
	public void saveDelete() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/me/device")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
				.param("token", "asdfghjk")
			).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.OK, saveRest.getCode());
	}

	@Test
	public void saveDeleteParamFail() throws Exception {
		// token
		String result = mvc.perform(MockMvcRequestBuilders.post("/me/device")
				.header("loginAccount", GsonUtil.create().toJson(loginAccount))
		).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.PARAM, saveRest.getCode());
		// account
		result = mvc.perform(MockMvcRequestBuilders.post("/me/device")
				.param("token", "asdfghjk")
		).andReturn().getResponse().getContentAsString();
		saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.UNAUTH, saveRest.getCode());
	}

	@BeforeEach
	public void before() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		loginAccount = Builder.set("person", Builder.set("id", 10L).to(new Person()))
				.set("extra", "SUPER")
				.to(new Account());
	}
}
