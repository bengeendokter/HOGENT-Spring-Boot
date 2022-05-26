package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

//Aantal tickets beschikbaar per wedstrijd
@Entity
@NamedQueries({
		@NamedQuery(name = "WedstrijdTicket.getTicketsByStadiumNaam", query = "SELECT w FROM WedstrijdTicket w WHERE w.stadium.naam = :stadiumNaam")})

public class WedstrijdTicket implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wedstrijd wedstrijd;
	
	private int tickets; //aantal tickets beschikbaar
	
	@ManyToOne
	private Stadium stadium;
	
	public WedstrijdTicket()
	{
	}
	
	public WedstrijdTicket(long id, Wedstrijd wedstrijd, int tickets)
	{
		this.id = id;
		this.wedstrijd = wedstrijd;
		this.tickets = tickets;
	}
	
	public int getTickets()
	{
		return tickets;
	}
	
	public Wedstrijd getWedstrijd()
	{
		return wedstrijd;
	}
	
	public Stadium getStadium()
	{
		return stadium;
	}
	
	public void setStadium(Stadium stadium)
	{
		this.stadium = stadium;
	}
	
	//We willen 'aantal' tickets kopen
	public int ticketsKopen(int aantal)
	{
		if(aantal <= 0)
		{
			return 0;
		}
		
		//Nog voldoende tickets
		if(tickets >= aantal)
		{
			tickets -= aantal;
			return aantal;
		}
		
		//Niet meer voldoende tickets
		int gekocht = tickets;
		tickets = 0;
		return gekocht;
	}
	
	public boolean uitverkocht()
	{
		return tickets == 0;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(wedstrijd);
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
		WedstrijdTicket other = (WedstrijdTicket) obj;
		return Objects.equals(wedstrijd, other.wedstrijd);
	}
	
	public Long getId()
	{
		return id;
	}
}
