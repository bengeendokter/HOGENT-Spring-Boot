package com.springBoot.firstExample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
//@ContextConfiguration(classes = WebConfig.class)
public class HelloControllerTest
{
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void before()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testHelloGet() throws Exception
	{
		
		mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(view().name("nameForm"))
				.andExpect(model().attributeExists("name"));
	}
	
	@Test
	public void testHelloPost() throws Exception
	{
		
		mockMvc.perform(post("/hello").param("value", "test")).andExpect(status().isOk())
				.andExpect(view().name("helloView")).andExpect(model().attributeExists("helloMessage"))
				.andExpect(model().attribute("helloMessage", "Hello test!"));
		
	}
}
