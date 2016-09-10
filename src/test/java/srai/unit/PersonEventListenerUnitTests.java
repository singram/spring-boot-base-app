package srai.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import srai.model.Person;
import srai.model.eventlistener.PersonEventListener;

/** PersonEventListener unit tests. */
@SuppressWarnings(
    { "PMD.JUnitTestsShouldIncludeAssert", "PMD.CommentRequired" })
public class PersonEventListenerUnitTests {
  private transient PersonEventListener personEventListener;
  private transient Person person;

  /** Setup PersonEventListener and Person test objects. */
  @Before
  public void setUp() {
    personEventListener = new PersonEventListener();
    person = new Person();
    person.setFirstName("Fred");
    person.setLastName("Blogs");
  }

  @Test
  public void shouldPrependFirstNameOnPrePersist() {
    final String expectedFirstName = "bar_" + person.getFirstName();
    personEventListener.prePersistActions(person);
    assertEquals(expectedFirstName, person.getFirstName());
  }

  @Test
  public void shouldNotChangeLasstNameOnPrePersist() {
    final String expectedLastName = person.getLastName();
    personEventListener.prePersistActions(person);
    assertEquals(expectedLastName, person.getLastName());
  }

}
