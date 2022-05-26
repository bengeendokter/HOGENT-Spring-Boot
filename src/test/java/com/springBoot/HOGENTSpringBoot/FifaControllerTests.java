package com.springBoot.HOGENTSpringBoot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.AankoopTicket;
import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.GenericDao;
import service.WedstrijdTicketDao;
import validation.AankoopTicketValidation;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class FifaControllerTests
{
	private FifaController controller;
	
	private MockMvc mockMvc;
	
	@Mock
	private MessageSource sourceMock;
	
	@Mock
	private WedstrijdTicketDao ticketMock;
	
	@Mock
	private GenericDao<Stadium> stadiumMock;
	
	@Autowired
	private AankoopTicketValidation validationMock;
	
	@BeforeEach
	public void before()
	{
		MockitoAnnotations.openMocks(this);
		controller = new FifaController();
		mockMvc = standaloneSetup(controller).build();
	}
	
	@Test
	public void testFifaGet() throws Exception
	{
		List<Stadium> expList = new ArrayList<>();
		Mockito.when(stadiumMock.findAll()).thenReturn(expList);
		ReflectionTestUtils.setField(controller, "stadiumDao", stadiumMock);
		
		mockMvc.perform(get("/fifa")).andExpect(view().name("stadiumForm"))
				.andExpect(model().attributeExists("stadiums")).andExpect(model().attributeExists("stadium"));
	}
	
	@Test
	public void testFifaPost() throws Exception
	{
		Stadium stadium = new Stadium();
		stadium.setNaam("test");
		
		List<WedstrijdTicket> expList = new ArrayList<>();
		Mockito.when(ticketMock.getTicketsByStadiumNaam("test")).thenReturn(expList);
		ReflectionTestUtils.setField(controller, "wedstrijdTicketDao", ticketMock);
		
		mockMvc.perform(post("/fifa").flashAttr("stadium", stadium)).andExpect(view().name("stadiumView"))
				.andExpect(model().attributeExists("stadiumNaam")).andExpect(model().attributeExists("ticketten"));
	}
	
	@Test
	public void testFifaIdGet() throws Exception
	{
		WedstrijdTicket expResult = new WedstrijdTicket(1l, new Wedstrijd(), 40);
		Mockito.when(ticketMock.get(1l)).thenReturn(expResult);
		ReflectionTestUtils.setField(controller, "wedstrijdTicketDao", ticketMock);
		
		List<Stadium> expList = new ArrayList<>();
		Mockito.when(stadiumMock.findAll()).thenReturn(expList);
		ReflectionTestUtils.setField(controller, "stadiumDao", stadiumMock);
		
		Locale locale = new Locale("BE_nl");
		Mockito.when(sourceMock.getMessage("tickets_uitverkocht", new Object[] {}, locale)).thenReturn("Message");
		ReflectionTestUtils.setField(controller, "messageSource", sourceMock);
		
		mockMvc.perform(get("/fifa/1").flashAttr("locale", locale)).andExpect(view().name("ticketForm"))
				.andExpect(model().attributeExists("aankoopTicket"))
				.andExpect(model().attributeExists("wedstrijdTicket"));
	}
	
	@Test
	public void testFifaIdPost() throws Exception
	{
		WedstrijdTicket expResult = new WedstrijdTicket(1l, new Wedstrijd(), 40);
		Mockito.when(ticketMock.get(1l)).thenReturn(expResult);
		ReflectionTestUtils.setField(controller, "wedstrijdTicketDao", ticketMock);
		
		List<Stadium> expList = new ArrayList<>();
		Mockito.when(stadiumMock.findAll()).thenReturn(expList);
		ReflectionTestUtils.setField(controller, "stadiumDao", stadiumMock);
		
		Locale locale = new Locale("BE_nl");
		Mockito.when(sourceMock.getMessage("error", new Object[] {}, locale)).thenReturn("Message");
		ReflectionTestUtils.setField(controller, "messageSource", sourceMock);
		
		AankoopTicket aankoopTicket = new AankoopTicket(expResult);
		aankoopTicket.setAantal(10);
		aankoopTicket.setEmail("ben.arts@student.hogent.be");
		ReflectionTestUtils.setField(controller, "aankoopTicketValidation", validationMock);
		
		mockMvc.perform(post("/fifa/1").flashAttr("locale", locale).flashAttr("aankoopTicket", aankoopTicket))
				.andExpect(view().name("redirect:/fifa")).andExpect(model().attributeExists("message"));
	}
}
