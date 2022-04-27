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
		Integer voetbalCode1 = aankoopTicket.getVoetbalCode1();
		Integer voetbalCode2 = aankoopTicket.getVoetbalCode2();
		
		if(voetbalCode1 == null || voetbalCode2 == null)
		{
			return;
		}
		
		if(voetbalCode1 >= voetbalCode2)
		{
			errors.rejectValue("voetbalCode1", "aankoopTicketValidation.voetbalCode1.voetbalCode2.message",
					"voetbalCode1 >= voetbalCode2");
		}
	}
}
