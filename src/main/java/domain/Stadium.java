package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stadium implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String naam;
	
	@OneToMany(mappedBy = "stadium", cascade = CascadeType.PERSIST)
	private List<WedstrijdTicket> wedstrijdTickets;
	
	public Stadium()
	{
		
	}
	
	public Stadium(String naam, List<WedstrijdTicket> wedstrijdTickets)
	{
		setNaam(naam);
		setWedstrijdTickets(wedstrijdTickets);
	}
	
	public String getNaam()
	{
		return naam;
	}
	
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(naam);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Stadium other = (Stadium) obj;
		return Objects.equals(naam, other.naam);
	}
	
	public List<WedstrijdTicket> getWedstrijdTickets()
	{
		return wedstrijdTickets;
	}
	
	public void setWedstrijdTickets(List<WedstrijdTicket> wedstrijdTickets)
	{
		this.wedstrijdTickets = wedstrijdTickets;
	}
}
