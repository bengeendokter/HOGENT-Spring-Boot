package service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import domain.WedstrijdTicket;

@Repository("wedstrijdTicketDao")
public class JpaWedstrijdTicketDao extends GenericDaoJpa<WedstrijdTicket> implements WedstrijdTicketDao
{
	
	public JpaWedstrijdTicketDao()
	{
		super(WedstrijdTicket.class);
	}
	
	@Override
	public int ticketsBestellen(Long id, int teBestellen)
	{
		WedstrijdTicket ticket = get(id);
		int gekocht = ticket.ticketsKopen(teBestellen);
		update(ticket);
		return gekocht;
	}
	
	@Override
	public List<WedstrijdTicket> getTicketsByStadiumNaam(String stadiumNaam)
	{
		TypedQuery<WedstrijdTicket> queryTicketsByStadium = em
				.createNamedQuery("WedstrijdTicket.getTicketsByStadiumNaam", WedstrijdTicket.class);
		queryTicketsByStadium.setParameter("stadiumNaam", stadiumNaam);
		return queryTicketsByStadium.getResultList();
	}
}
