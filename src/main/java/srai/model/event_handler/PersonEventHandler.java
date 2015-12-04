package srai.model.event_handler;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import srai.model.Person;

@Component
@RepositoryEventHandler(Person.class)
public class PersonEventHandler {

	@HandleBeforeCreate
	public void namePrepender(Person person)
	{
		person.setFirstName("bar_"+person.getFirstName());
	}

}

