package srai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import srai.model.Person;
import srai.model.validation.PersonValidator;

@Configuration
@EnableJpaAuditing
public class ApplicationRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	@Autowired
	private PersonValidator personValidator;

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Person.class);
		config.setReturnBodyOnCreate(true);
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", personValidator);
	}
}
