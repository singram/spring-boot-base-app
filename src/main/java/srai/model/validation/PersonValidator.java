package srai.model.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import srai.model.Person;

@Component
public class PersonValidator implements Validator {

	/**
	 * This Validator validates *just* Person instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
		ValidationUtils.rejectIfEmpty(e, "lastName", "lastName.empty");
		/*		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
		 */
	}
}