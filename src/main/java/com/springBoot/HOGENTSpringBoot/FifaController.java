package com.springBoot.HOGENTSpringBoot;

import java.util.List;
import java.util.Locale;

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
import service.VoetbalService;
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
	private VoetbalService voetbalService;
	
	@Autowired
	private WedstrijdTicketDao wedstrijdTicketDao;
	
	@Autowired
	private AankoopTicketValidation aankoopTicketValidation;
	
//	constructor met populate dp?
	
//	@EventListener
//	public void seed(ContextRefreshedEvent event)
//	{
//		new PopulateDB().run();
//	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event)
	{
		wedstrijdTicketDao
				.insert(new WedstrijdTicket(new Wedstrijd("1", new String[] {"België", "Canada"}, 26, 21), 35));
	}
	
	@GetMapping
	public String stadiumForm(@RequestParam(value = "verkocht", required = false) Integer verkocht, Model model,
			Locale locale)
	{
//		wedstrijdTicketDao
//				.insert(new WedstrijdTicket(new Wedstrijd("1", new String[] {"België", "Canada"}, 26, 21), 35));
		
		model.addAttribute("stadiums", voetbalService.getStadiumList());
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
		model.addAttribute("ticketten", voetbalService.getWedstrijdenByStadium(stadium.getNaam()));
		
		return "stadiumView";
	}
	
	@GetMapping(value = "/{id}")
	public String wedstrijdForm(@PathVariable("id") String id, Model model, Locale locale)
	{
		List<String> stadiums = voetbalService.getStadiumList();
		WedstrijdTicket wedstrijdTicket = voetbalService.getWedstrijd(id);
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		if(wedstrijdTicket.uitverkocht())
		{
			// TODO zelfde propleem, kan ik redirect doen en model attributes extra meegeven?
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
			wedstrijden = voetbalService.getWedstrijdenByStadium(stadium);
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
	public String wedstrijdUpdate(@PathVariable("id") String id, @Valid AankoopTicket aankoopTicket,
			BindingResult result, Model model, Locale locale)
	{
		List<String> stadiums = voetbalService.getStadiumList();
		WedstrijdTicket wedstrijdTicket = voetbalService.getWedstrijd(id);
		
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
				wedstrijden = voetbalService.getWedstrijdenByStadium(stadium);
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
		int gekocht = wedstrijdTicket.ticketsKopen(aantal);
		
		return "redirect:/fifa?verkocht=" + gekocht;
	}
}
