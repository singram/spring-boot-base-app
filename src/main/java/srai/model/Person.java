package srai.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import srai.model.eventlistener.PersonEventListener;

/** Person data model. */
@Entity
@Table(name = "people")
@EntityListeners(PersonEventListener.class)
public class Person extends CommonBaseModel {

  /** First name. */
  private String firstName;

  /** Last name. */
  private String lastName;

  /** Thoughts a person has. */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Thought> thoughts = new ArrayList<Thought>();

  /** Children a person has. */
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Person> children = new HashSet<Person>();

  /** Parent of person. */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
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
  public Set<Person> getChildren() {
    return children;
  }

  /**
   * Children setter.
   * @param children the children to set.
   */
  public void setChildren(final Set<Person> children) {
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
