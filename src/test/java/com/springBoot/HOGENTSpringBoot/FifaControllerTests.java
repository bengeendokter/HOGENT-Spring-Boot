package com.springBoot.HOGENTSpringBoot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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
@TestInstance(Lifecycle.PER_CLASS)
class FifaControllerTests
{
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeAll
	public void before()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testFifaGet() throws Exception
	{
		mockMvc.perform(get("/fifa")).andExpect(view().name("stadiumForm"))
				.andExpect(model().attributeExists("stadiums")).andExpect(model().attributeExists("stadium"));
	}
	
	@Test
	public void testFifaPost() throws Exception
	{
//		Stadium stadium = new Stadium();
//		stadium.setNaam("test");
		// TODO vraag hoe non string parameters meegeven
		mockMvc.perform(post("/fifa")).andExpect(view().name("stadiumView"))
				.andExpect(model().attributeExists("stadiumNaam")).andExpect(model().attributeExists("ticketten"));
	}
}
