package domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

//Een wedstrijd
@Entity
public class Wedstrijd implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id; //unieke sleutel
	
	private String[] landen; //2 landen van de wedstrijd
	
	private LocalDateTime dateTime;
	
	public Wedstrijd()
	{
	}
	
	public Wedstrijd(Long id, String[] landen, LocalDateTime dateTime)
	{
		this.id = id;
		this.landen = landen;
		this.dateTime = dateTime;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public String[] getLanden()
	{
		return landen;
	}
	
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s vs %s op %d-%d", landen[0], landen[1], dateTime.getDayOfMonth(),
				dateTime.getMonthValue());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(id);
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
		Wedstrijd other = (Wedstrijd) obj;
		return Objects.equals(id, other.id);
	}
}
