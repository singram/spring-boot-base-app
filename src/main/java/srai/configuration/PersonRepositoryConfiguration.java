package srai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import srai.model.validation.PersonValidator;

@Configuration
public class PersonRepositoryConfiguration extends  RepositoryRestConfigurerAdapter {

	@Autowired
	private PersonValidator personValidator;

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", personValidator);
	}
}
