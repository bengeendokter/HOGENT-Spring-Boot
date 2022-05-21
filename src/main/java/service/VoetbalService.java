package service;

import java.util.List;

import domain.WedstrijdTicket;

public interface VoetbalService
{
	
	// stadiumDao.findAll()
	public List<String> getStadiumList();
	
	// wedstijdTicketDao.getWedstrijdenByStadium(String stadium)
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium);
	
	// wedstijdTicketDao.get(String id)
	public WedstrijdTicket getWedstrijd(String id);
	
	// wedstijdTicketDao.ticketsBestellen(String id, int teBestellen)
	public int ticketsBestellen(String id, int teBestellen);
	
}
