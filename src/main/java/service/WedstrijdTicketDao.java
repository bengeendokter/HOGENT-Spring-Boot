package service;

import domain.WedstrijdTicket;

public interface WedstrijdTicketDao extends GenericDao<WedstrijdTicket>
{
	public int ticketsBestellen(String id, int teBestellen);
}
