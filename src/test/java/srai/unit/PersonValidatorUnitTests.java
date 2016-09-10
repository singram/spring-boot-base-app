package srai.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import srai.model.Person;
import srai.model.validation.PersonValidator;

/** PersonValidator unit tests. */
@SuppressWarnings(
    { "PMD.JUnitTestsShouldIncludeAssert", "PMD.CommentRequired",
      "PMD.SignatureDeclareThrowsException" })
public class PersonValidatorUnitTests {

  private transient Validator personValidator;
  private transient Person person;
  private transient BindException errors;

  /** Setup PersonValidator and Person test objects. */
  @Before
  public void setUp() {
    personValidator = new PersonValidator();
    person = new Person();
    person.setFirstName("Fred");
    person.setLastName("Blogs");
    errors = new BindException(person, "person");
  }

  @Test
  public void supports() {
    assertTrue(personValidator.supports(Person.class));
    assertFalse(personValidator.supports(Object.class));
  }

  @Test
  public void shouldBeValid() throws Exception {
    ValidationUtils.invokeValidator(personValidator, person, errors);
    assertFalse(errors.hasErrors());
  }

  @Test
  public void shouldBeInvalidWithEmptyFirstName() {
    person.setFirstName(null);
    ValidationUtils.invokeValidator(personValidator, person, errors);
    assertTrue(errors.hasErrors());
    assertEquals(1, errors.getFieldErrorCount("firstName"));
  }

  @Test
  public void shouldBeInvalidWithEmptyLastName() {
    person.setLastName(null);
    ValidationUtils.invokeValidator(personValidator, person, errors);
    assertTrue(errors.hasErrors());
    assertEquals(1, errors.getFieldErrorCount("lastName"));
  }

}
