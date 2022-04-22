package com.springBoot.HOGENTSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Stadium;
import service.VoetbalService;

@Controller
@RequestMapping("/fifa")
public class FifaController
{
	
	@Autowired
	private VoetbalService voetbalService;
	
	@GetMapping
	public String showFormPage(Model model)
	{
		model.addAttribute("stadiums", voetbalService.getStadiumList());
		model.addAttribute("stadium", new Stadium());
		return "stadiumForm";
	}
	
	@PostMapping
	public String onSubmit(@ModelAttribute Stadium stadium, Model model)
	{
		System.out.println(stadium.getNaam()); // null
		model.addAttribute("stadiumNaam", stadium.getNaam());
		return "stadiumView";
	}
	
}
