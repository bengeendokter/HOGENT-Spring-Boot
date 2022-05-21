package service;

import java.util.List;

import domain.Stadium;
import domain.WedstrijdTicket;

public interface StadiumDao extends GenericDao<Stadium>
{
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium);
}
