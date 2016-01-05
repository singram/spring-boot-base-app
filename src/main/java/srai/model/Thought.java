package srai.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Thought data model. */
@Entity
@Table(name = "thoughts")
public class Thought extends CommonBaseModel {

  /** Person thought belongs to. */
  @ManyToOne(fetch = FetchType.LAZY)
  private Person person;

  /** Thought data. */
  private String data;

  /** Data getter. */
  public String getData() {
    return data;
  }

  /** Data setter. */
  public void setData(final String data) {
    this.data = data;
  }

  /** Person getter. */
  public Person getPerson() {
    return person;
  }

  /** Person setter. */
  public void setPerson(final Person person) {
    this.person = person;
  }

}