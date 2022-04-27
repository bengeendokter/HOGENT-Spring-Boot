package com.springBoot.HOGENTSpringBoot;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.AankoopTicket;
import domain.Stadium;
import domain.WedstrijdTicket;
import service.VoetbalService;
import validation.AankoopTicketValidation;

@Controller
@RequestMapping("/fifa")
public class FifaController
{
	
	@Autowired
	private VoetbalService voetbalService;
	
	@Autowired
	private AankoopTicketValidation aankoopTicketValidation;
	
	@GetMapping
	public String stadiumForm(Model model)
	{
		model.addAttribute("stadiums", voetbalService.getStadiumList());
		model.addAttribute("stadium", new Stadium());
		
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
	public String wedstrijdForm(@PathVariable("id") String id, Model model)
	{
		WedstrijdTicket wedstrijdTicket = voetbalService.getWedstrijd(id);
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		List<String> stadiums = voetbalService.getStadiumList();
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
		WedstrijdTicket wedstrijdTicket = voetbalService.getWedstrijd(id);
		if(wedstrijdTicket == null)
		{
			return "redirect:/fifa";
		}
		
		aankoopTicketValidation.validate(aankoopTicket, result);
		
		if(result.hasErrors())
		{
			// TODO did doen of redirect en aankoopTicket toevoegen in GetMapping?
			List<String> stadiums = voetbalService.getStadiumList();
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
		
		// TODO boodshap aantal gekocht
//		model.addAttribute("message",
//				new Message("error", messageSource.getMessage("contact_save_fail", new Object[] {}, locale)));
//		
		return "redirect:/fifa";
	}
}
