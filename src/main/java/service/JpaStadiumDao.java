package service;

import java.util.List;

import org.springframework.stereotype.Repository;

import domain.Stadium;
import domain.WedstrijdTicket;

@Repository("stadiumDao")
public class JpaStadiumDao extends GenericDaoJpa<Stadium> implements StadiumDao
{
	
	public JpaStadiumDao()
	{
		super(Stadium.class);
	}
	
	@Override
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
