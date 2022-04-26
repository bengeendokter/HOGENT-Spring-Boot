package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AankoopTicket
{
	private WedstrijdTicket wedstrijdTicket;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Min(value = 1, message = "{aankoopTicket.aantal.min.message}")
	@Max(value = 25, message = "{aankoopTicket.aantal.max.message}")
	private int aantal;
	
	@NotNull
	private int voetbalCode1;
	
	@NotNull
	private int voetbalCode2;
	
	public AankoopTicket(WedstrijdTicket wedstrijdTicket)
	{
		this.wedstrijdTicket = wedstrijdTicket;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public int getAantal()
	{
		return aantal;
	}
	
	public void setAantal(int aantal)
	{
		this.aantal = aantal;
	}
	
	public int getVoetbalCode1()
	{
		return voetbalCode1;
	}
	
	public void setVoetbalCode1(int voetbalCode1)
	{
		this.voetbalCode1 = voetbalCode1;
	}
	
	public int getVoetbalCode2()
	{
		return voetbalCode2;
	}
	
	public void setVoetbalCode2(int voetbalCode2)
	{
		this.voetbalCode2 = voetbalCode2;
	}
}
