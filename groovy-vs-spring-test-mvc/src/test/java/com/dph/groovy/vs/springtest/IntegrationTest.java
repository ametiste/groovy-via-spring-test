package com.dph.groovy.vs.springtest;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/app-config.xml", "classpath:spring/groovy-vs-spring-test-servlet.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@DirtiesContext
public class IntegrationTest {

    @Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	@Qualifier("secondService")
	private PrintStringService service;


    @Before
	public void setup() throws IOException, URISyntaxException {

		this.mockMvc = webAppContextSetup(this.wac).build();
	}


	@Test
	public void test() throws Exception {

		this.mockMvc
				.perform(
						get("/").accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("Hello"))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		;
	}

}
