package com.springBoot.HOGENTSpringBoot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.AankoopTicket;
import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.GenericDao;
import service.WedstrijdTicketDao;
import utility.Message;
import validation.AankoopTicketValidation;

@Controller
@RequestMapping("/fifa")

@SessionAttributes("message")
public class FifaController
{
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private WedstrijdTicketDao wedstrijdTicketDao;
	
	@Autowired
	private GenericDao<Stadium> stadiumDao;
	
	@Autowired
	private AankoopTicketValidation aankoopTicketValidation;
	
	@GetMapping
	public String stadiumForm(Model model, Locale locale)
	{
		model.addAttribute("stadiums",
				stadiumDao.findAll().stream().map(Stadium::toString).collect(Collectors.toList()));
		model.addAttribute("stadium", new Stadium());
		
		return "stadiumForm";
	}
	
	@PostMapping
	public String stadiumView(@ModelAttribute Stadium stadium, Model model)
	{
		// vanaf dat dit scherm bereikt is, reset message session attribute voor stadiumForm
		model.addAttribute("message", new Message());
		model.addAttribute("stadiumNaam", stadium.getNaam());
		model.addAttribute("ticketten", wedstrijdTicketDao.getTicketsByStadiumNaam(stadium.getNaam()));
		
		return "stadiumView";
	}
	
	@GetMapping(value = "/{id}")
	public String wedstrijdForm(@PathVariable("id") Long id, Model model, Locale locale)
	{
		// vanaf dat dit scherm bereikt is, reset message session attribute voor stadiumForm
		model.addAttribute("message", new Message());
		List<String> stadiums = stadiumDao.findAll().stream().map(Stadium::toString).collect(Collectors.toList());
		WedstrijdTicket wedstrijdTicket = wedstrijdTicketDao.get(id);
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		if(wedstrijdTicket.uitverkocht())
		{
			model.addAttribute("message",
					new Message("error", messageSource.getMessage("tickets_uitverkocht", new Object[] {}, locale)));
			
			return "redirect:/fifa";
		}
		
		List<WedstrijdTicket> wedstrijden;
		String stadiumNaam = "";
		
		for(String stadium : stadiums)
		{
			wedstrijden = wedstrijdTicketDao.getTicketsByStadiumNaam(stadium);
			if(wedstrijden.contains(wedstrijdTicket))
			{
				stadiumNaam = stadium;
			}
		}
		
		model.addAttribute("stadiumNaam", stadiumNaam);
		model.addAttribute("wedstrijdTicket", wedstrijdTicket);
		model.addAttribute("aankoopTicket", new AankoopTicket(wedstrijdTicket));
		return "ticketForm";
	}
	
	@PostMapping(value = "/{id}")
	public String wedstrijdUpdate(@PathVariable("id") Long id, @Valid AankoopTicket aankoopTicket, BindingResult result,
			Model model, Locale locale)
	{
		List<String> stadiums = stadiumDao.findAll().stream().map(Stadium::toString).collect(Collectors.toList());
		WedstrijdTicket wedstrijdTicket = wedstrijdTicketDao.get(id);
		
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		aankoopTicketValidation.validate(aankoopTicket, result);
		
		if(result.hasErrors())
		{
			List<WedstrijdTicket> wedstrijden;
			String stadiumNaam = "";
			
			for(String stadium : stadiums)
			{
				wedstrijden = wedstrijdTicketDao.getTicketsByStadiumNaam(stadium);
				if(wedstrijden.contains(wedstrijdTicket))
				{
					stadiumNaam = stadium;
				}
			}
			
			model.addAttribute("stadiumNaam", stadiumNaam);
			model.addAttribute("wedstrijdTicket", wedstrijdTicket);
			return "ticketForm";
		}
		
		int aantal = aankoopTicket.getAantal();
		int gekocht = wedstrijdTicketDao.ticketsBestellen(wedstrijdTicket.getId(), aantal);
		
		String code = gekocht == 1 ? "ticket_gekocht" : "tickets_gekocht";
		model.addAttribute("message",
				new Message("error", messageSource.getMessage(code, new Object[] {gekocht}, locale)));
		
		return "redirect:/fifa";
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event)
	{
		WedstrijdTicket ticket1 = new WedstrijdTicket(1l,
				new Wedstrijd(1l, new String[] {"België", "Canada"}, LocalDateTime.of(2022, 11, 26, 21, 0)), 35);
		WedstrijdTicket ticket2 = new WedstrijdTicket(2l,
				new Wedstrijd(2l, new String[] {"Brazilië", "Zwitserland"}, LocalDateTime.of(2022, 11, 27, 18, 0)), 0);
		WedstrijdTicket ticket3 = new WedstrijdTicket(3l,
				new Wedstrijd(3l, new String[] {"Marroko", "Kroatië"}, LocalDateTime.of(2022, 11, 28, 15, 0)), 5);
		WedstrijdTicket ticket4 = new WedstrijdTicket(4l,
				new Wedstrijd(4l, new String[] {"Spanje", "Duitsland"}, LocalDateTime.of(2022, 11, 28, 18, 0)), 95);
		WedstrijdTicket ticket5 = new WedstrijdTicket(5l,
				new Wedstrijd(5l, new String[] {"Frankrijk", "Denemarken"}, LocalDateTime.of(2022, 11, 30, 15, 0)), 45);
		WedstrijdTicket ticket6 = new WedstrijdTicket(6l,
				new Wedstrijd(6l, new String[] {"Argentinië", "Mexico"}, LocalDateTime.of(2022, 11, 30, 18, 0)), 10);
		WedstrijdTicket ticket7 = new WedstrijdTicket(7l,
				new Wedstrijd(7l, new String[] {"Engeland", "USA"}, LocalDateTime.of(2022, 11, 30, 18, 0)), 22);
		WedstrijdTicket ticket8 = new WedstrijdTicket(8l,
				new Wedstrijd(8l, new String[] {"Nederland", "Qatar"}, LocalDateTime.of(2022, 11, 30, 21, 0)), 16);
		
		Stadium stadium1 = new Stadium("Al Bayt Stadium",
				new ArrayList<>(Arrays.asList(ticket1, ticket2, ticket3, ticket6, ticket7)));
		ticket1.setStadium(stadium1);
		ticket2.setStadium(stadium1);
		ticket3.setStadium(stadium1);
		ticket6.setStadium(stadium1);
		ticket7.setStadium(stadium1);
		
		Stadium stadium2 = new Stadium("Al Thumama Stadium", new ArrayList<>(Arrays.asList(ticket4, ticket5, ticket8)));
		ticket4.setStadium(stadium2);
		ticket5.setStadium(stadium2);
		ticket8.setStadium(stadium2);
		
		stadiumDao.insert(stadium1);
		stadiumDao.insert(stadium2);
	}
}
