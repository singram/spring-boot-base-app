package srai.model.eventlistener;

import javax.persistence.PrePersist;

import srai.model.Person;

/** Person model persistence lifecycle event handler. */
public class PersonEventListener {

  /** Pre persist event handler. */
  @PrePersist
  public void prePersistActions(final Person person) {
    namePrepender(person);
  }

  /** Prepends a persons name before creation. */
  public void namePrepender(final Person person) {
    person.setFirstName("bar_" + person.getFirstName());
  }

}

