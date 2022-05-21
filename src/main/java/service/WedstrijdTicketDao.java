package service;

import java.util.List;

import domain.WedstrijdTicket;

public interface WedstrijdTicketDao extends GenericDao<WedstrijdTicket>
{
	public int ticketsBestellen(String id, int teBestellen);
	
	public List<WedstrijdTicket> getTicketsByStadiumNaam(String stadiumNaam);
}
