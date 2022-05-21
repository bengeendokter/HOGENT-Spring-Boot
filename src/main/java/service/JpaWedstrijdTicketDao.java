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
	public int ticketsBestellen(String id, int teBestellen)
	{
		// TODO Auto-generated method stub
		return 0;
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
