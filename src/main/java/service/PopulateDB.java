package service;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Wedstrijd;
import domain.WedstrijdTicket;

public class PopulateDB
{
	@Autowired
	private WedstrijdTicketDao wedstrijdTicketDao;
	
	public void run()
	{
		wedstrijdTicketDao
				.insert(new WedstrijdTicket(new Wedstrijd("1", new String[] {"België", "Canada"}, 26, 21), 35));
	}
}
