package service;

import org.springframework.stereotype.Repository;

import domain.WedstrijdTicket;

@Repository("wedstrijdTicketDao")
public class JpaWedstrijdTicketDao extends GenericDaoJpa<WedstrijdTicket> implements WedstrijdTicketDao
{
	
	public JpaWedstrijdTicketDao()
	{
		super(WedstrijdTicket.class);
	}
}
