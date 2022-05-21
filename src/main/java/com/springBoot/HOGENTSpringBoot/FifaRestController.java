package com.springBoot.HOGENTSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Wedstrijd;
import service.GenericDao;

@RestController
@RequestMapping("/fifaDetail")
public class FifaRestController
{
	@Autowired
	private GenericDao<Wedstrijd> wedstrijdDao;
	
	@GetMapping(value = "/{wedstrijdId}")
	public String[] getWedstrijdDetail(@PathVariable("wedstrijdId") Long wedstrijdId)
	{
		return wedstrijdDao.get(wedstrijdId).getLanden();
	}
}
