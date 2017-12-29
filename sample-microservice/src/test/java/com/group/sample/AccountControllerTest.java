package com.group.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AccountControllerTest {

	private MockMvc mockMvc;

	@Resource
	private WebApplicationContext webApplicationContext;

//	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	//@Test
	public void test() throws Exception {
		mockMvc.perform(get("/accounts/test")).andExpect(status().isNoContent());

	}
	//@Test
	public void testAccounts() throws Exception {
		mockMvc.perform(get("/accounts")).andExpect(status().isOk());

	}

}
