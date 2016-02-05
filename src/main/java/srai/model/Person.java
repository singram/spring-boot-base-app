package srai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Person data model. */
@Entity
@Table(name = "people")
public class Person extends CommonBaseModel {

  /** First name. */
  private String firstName;

  /** Last name. */
  private String lastName;

  /** Thoughts a person has. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private List<Thought> thoughts;

  /** Children a person has. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private List<Person> children;

  /** Parent of person. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Person parent;

  /** First name getter. */
  public String getFirstName() {
    return this.firstName;
  }

  /** First name setter. */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /** Last name getter. */
  public String getLastName() {
    return this.lastName;
  }

  /** Last name setter. */
  public void setLastName(final String lastname) {
    this.lastName = lastname;
  }

  /** String representation. */
  @Override
  public String toString() {
    return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName
        + "]";
  }

  /** Thoughts getter. */
  public List<Thought> getThoughts() {
    return thoughts;
  }

  /** Thoughts setter. */
  public void setThoughts(final List<Thought> thoughts) {
    this.thoughts = thoughts;
  }

  /**
   * Children getter.
   * @return the children.
   */
  public List<Person> getChildren() {
    return children;
  }

  /**
   * Children setter.
   * @param children the children to set.
   */
  public void setChildren(final List<Person> children) {
    this.children = children;
  }

  /**
   * Parent getter.
   * @return the parent.
   */
  public Person getParent() {
    return parent;
  }

  /**
   * Parent setter.
   * @param parent the parent to set.
   */
  public void setParent(final Person parent) {
    this.parent = parent;
  }

}
