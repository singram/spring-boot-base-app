package srai.actuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import srai.actuator.properties.GitProperties;

/** Simple actuator extension to expose git properties. */
@Component
@ConfigurationProperties(prefix = "endpoints.git", ignoreUnknownFields = false)
public class GitEndpoint extends AbstractEndpoint<GitProperties> {

  /** Git properties source. */
  @Autowired
  private transient GitProperties gitProperties;

  /** Constructor. */
  public GitEndpoint() {
    super("git");
  }

  /** Endpoint identifier. */
  @Override
  public String getId() {
    return "git";
  }

  /** Endpoint payload when invoked through actuator framework. */
  @Override
  public GitProperties invoke() {
    return gitProperties;
  }
}
