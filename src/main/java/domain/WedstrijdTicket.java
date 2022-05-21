package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//Aantal tickets beschikbaar per wedstrijd
@Entity
public class WedstrijdTicket implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wedstrijd wedstrijd;
	
	private int tickets; //aantal tickets beschikbaar
	
	@ManyToOne
	private Stadium stadium;
	
	public WedstrijdTicket()
	{
	}
	
	public WedstrijdTicket(Wedstrijd wedstrijd, int tickets)
	{
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
}
