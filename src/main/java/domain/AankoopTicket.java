package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AankoopTicket
{
	private WedstrijdTicket wedstrijdTicket;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private int aantal;
	
	@NotEmpty
	private int voetbalCode1;
	
	@NotEmpty
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
