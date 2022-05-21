package com.springBoot.HOGENTSpringBoot;

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
import org.springframework.web.bind.annotation.RequestParam;

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
	public String stadiumForm(@RequestParam(value = "verkocht", required = false) Integer verkocht, Model model,
			Locale locale)
	{
		model.addAttribute("stadiums",
				stadiumDao.findAll().stream().map(Stadium::toString).collect(Collectors.toList()));
		model.addAttribute("stadium", new Stadium());
		
		if(verkocht != null)
		{
			String code = verkocht == 1 ? "ticket_gekocht" : "tickets_gekocht";
			model.addAttribute("message",
					new Message("error", messageSource.getMessage(code, new Object[] {verkocht}, locale)));
		}
		
		return "stadiumForm";
	}
	
	@PostMapping
	public String stadiumView(@ModelAttribute Stadium stadium, Model model)
	{
		model.addAttribute("stadiumNaam", stadium.getNaam());
		model.addAttribute("ticketten", wedstrijdTicketDao.getTicketsByStadiumNaam(stadium.getNaam()));
		
		return "stadiumView";
	}
	
	@GetMapping(value = "/{id}")
	public String wedstrijdForm(@PathVariable("id") Long id, Model model, Locale locale)
	{
		List<String> stadiums = stadiumDao.findAll().stream().map(Stadium::toString).collect(Collectors.toList());
		WedstrijdTicket wedstrijdTicket = wedstrijdTicketDao.get(id);
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		if(wedstrijdTicket.uitverkocht())
		{
			// TODO doe redirect want anders error
			// TODO zelfde probleem, kan ik redirect doen en model attributes extra meegeven?
			model.addAttribute("message",
					new Message("error", messageSource.getMessage("tickets_uitverkocht", new Object[] {}, locale)));
			model.addAttribute("stadiums", stadiums);
			model.addAttribute("stadium", new Stadium());
			
			return "stadiumForm";
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
			// TODO dit doen of redirect en aankoopTicket toevoegen in GetMapping?
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
		
		return "redirect:/fifa?verkocht=" + gekocht;
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event)
	{
		WedstrijdTicket ticket1 = new WedstrijdTicket(new Wedstrijd(1l, new String[] {"België", "Canada"}, 26, 21), 35);
		WedstrijdTicket ticket2 = new WedstrijdTicket(
				new Wedstrijd(2l, new String[] {"Brazilië", "Zwitserland"}, 27, 18), 0);
		WedstrijdTicket ticket3 = new WedstrijdTicket(new Wedstrijd(3l, new String[] {"Marroko", "Kroatië"}, 28, 15),
				5);
		WedstrijdTicket ticket4 = new WedstrijdTicket(new Wedstrijd(4l, new String[] {"Spanje", "Duitsland"}, 28, 18),
				95);
		WedstrijdTicket ticket5 = new WedstrijdTicket(
				new Wedstrijd(5l, new String[] {"Frankrijk", "Denemarken"}, 30, 15), 45);
		WedstrijdTicket ticket6 = new WedstrijdTicket(new Wedstrijd(6l, new String[] {"Argentinië", "Mexico"}, 30, 18),
				10);
		WedstrijdTicket ticket7 = new WedstrijdTicket(new Wedstrijd(7l, new String[] {"Engeland", "USA"}, 31, 18), 22);
		WedstrijdTicket ticket8 = new WedstrijdTicket(new Wedstrijd(8l, new String[] {"Nederland", "Qatar"}, 31, 21),
				16);
		
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
