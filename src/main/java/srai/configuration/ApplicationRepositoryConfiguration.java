package srai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import srai.model.Person;
import srai.model.validation.PersonValidator;

/** Application repository configuration. */
@Configuration
@EnableJpaAuditing
public class ApplicationRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

  /** Person model validator. */
  @Autowired
  private transient PersonValidator personValidator;

  /** Spring rest repository behavior configuration. */
  @Override
  public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
    config.exposeIdsFor(Person.class);
    config.setReturnBodyOnCreate(true);
  }

  /** Validation event configuration. */
  @Override
  public void configureValidatingRepositoryEventListener(
      final ValidatingRepositoryEventListener validatingListener) {
    validatingListener.addValidator("beforeCreate", personValidator);
  }
}
