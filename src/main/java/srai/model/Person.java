package srai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	private List<Thought> thoughts;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName
				+ "]";
	}

	public List<Thought> getThoughts() {
		return thoughts;
	}

	public void setThoughts(List<Thought> thoughts) {
		this.thoughts = thoughts;
	}
}
