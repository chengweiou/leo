package chengweiou.universe.leo.controller;


import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.blackhole.model.Rest;
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

	@Test
	public void push() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/api/push")
				.param("person.id", "1")
				.param("name", "controller-test-title")
				.param("content", "controller-test-body-person")
			).andReturn().getResponse().getContentAsString();
		Rest<Long> saveRest = Rest.from(result);
		Assertions.assertEquals(BasicRestCode.OK, saveRest.getCode());
	}

	@Test
	public void pushTop() throws Exception {
		String result = mvc.perform(MockMvcRequestBuilders.post("/api/push/topic")
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
	}
}
