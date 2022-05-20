package service;

import java.util.List;

import org.springframework.stereotype.Repository;

import domain.WedstrijdTicket;

@Repository("wedstrijdTicketDao")
public class JpaWedstrijdTicketDao extends GenericDaoJpa<WedstrijdTicket> implements WedstrijdTicketDao, VoetbalService
{
	
	public JpaWedstrijdTicketDao()
	{
		super(WedstrijdTicket.class);
	}
	
	@Override
	public List<String> getStadiumList()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WedstrijdTicket getWedstrijd(String id)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int ticketsBestellen(String id, int teBestellen)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
