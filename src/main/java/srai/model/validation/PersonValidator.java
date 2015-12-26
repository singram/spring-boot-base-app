package srai.model.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import srai.model.Person;

/** Validated for Person model. */
@Component
public class PersonValidator implements Validator {

  /**
   * This Validator validates *just* Person instances.
   */
  @Override
  public boolean supports(final Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  /** Validation of person model. */
  @Override
  public void validate(final Object obj, final Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
    /* Person p = (Person) obj;
       if (p.getAge() < 0) {
          e.rejectValue("age", "negativevalue");
       } else if (p.getAge() > 110) {
          e.rejectValue("age", "too.darn.old");
       }
     */
  }
}