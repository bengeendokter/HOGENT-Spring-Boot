package validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.AankoopTicket;

public class AankoopTicketValidation implements Validator
{
	@Override
	public boolean supports(Class<?> clazz)
	{
		return AankoopTicket.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		AankoopTicket aankoopTicket = (AankoopTicket) target;
		String aantalStr = aankoopTicket.getAantal();
		String voetbalCode1Str = aankoopTicket.getVoetbalCode1();
		String voetbalCode2Str = aankoopTicket.getVoetbalCode2();
		
		int voetbalCode1;
		int voetbalCode2;
		
		try
		{
			int aantal = Integer.parseInt(aantalStr);
		}
		catch(NumberFormatException e)
		{
			errors.rejectValue("aantal", "aankoopTicketValidation.aantal.typeMismatch.message", "aantal is geen getal");
		}
		
		try
		{
			voetbalCode1 = Integer.parseInt(voetbalCode1Str);
		}
		catch(NumberFormatException e)
		{
			errors.rejectValue("voetbalCode1", "aankoopTicketValidation.voetbalCode1.typeMismatch.message",
					"voetbalCode1 is geen getal");
		}
		
		try
		{
			voetbalCode2 = Integer.parseInt(voetbalCode2Str);
		}
		catch(NumberFormatException e)
		{
			errors.rejectValue("voetbalCode2", "aankoopTicketValidation.voetbalCode2.typeMismatch.message",
					"voetbalCode2 is geen getal");
		}
		
		if(voetbalCode1 >= voetbalCode2)
		{
			errors.rejectValue("voetbalCode1", "aankoopTicketValidation.voetbalCode1.voetbalCode2.message",
					"voetbalCode1 >= voetbalCode2");
		}
	}
}
