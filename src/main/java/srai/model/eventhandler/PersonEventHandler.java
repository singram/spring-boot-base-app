package srai.model.eventhandler;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import srai.model.Person;

/** Person model persistence lifecycle event handler. */
@Component
@RepositoryEventHandler(Person.class)
public class PersonEventHandler {

  /** Prepends a persons name before creation. */
  @HandleBeforeCreate
  public void namePrepender(final Person person) {
    person.setFirstName("bar_" + person.getFirstName());
  }

}

