package com.springBoot.firstExample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.springBoot.HOGENTSpringBoot.HelloController;

import domain.HelloService;

public class HelloControllerMockTest
{
	
	private HelloController controller;
	
	private MockMvc mockMvc;
	
	@Mock
	private HelloService mock;
	
	@BeforeEach
	public void before()
	{
		MockitoAnnotations.openMocks(this);
		controller = new HelloController();
		mockMvc = standaloneSetup(controller).build();
	}
	
	@Test
	public void testHelloPost() throws Exception
	{
		
		//Indien HelloService nog niet ge�mplementeerd is, of de klasse gebruik maakt van databank of webservices
		//oplossing = Mockito
		String expResult = "Hello testMock!";
		Mockito.when(mock.sayHello("test")).thenReturn(expResult);
		//injectie:
		ReflectionTestUtils.setField(controller, "helloService", mock);
		
		mockMvc.perform(post("/hello").param("value", "test")).andExpect(view().name("helloView"))
				.andExpect(model().attributeExists("helloMessage"))
				.andExpect(model().attribute("helloMessage", expResult));
		
	}
}
