package com.springBoot.HOGENTSpringBoot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import domain.HelloService;

@Controller
public class HelloController
{
	
	@Autowired
	private HelloService helloService;
	
	@GetMapping("/hello")
	public String showFormPage(Model model)
	{
		model.addAttribute("name", new Name());
		return "nameForm";
	}
	
	@PostMapping("/hello")
	public String onSubmit(@ModelAttribute Name name, Model model)
	{
		
		model.addAttribute("helloMessage", helloService.sayHello(name.getValue()));
		return "helloView";
	}
	
}
