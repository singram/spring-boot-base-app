package srai.actuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import srai.actuator.properties.GitProperties;

@Component
//public class gitEndpoint implements Endpoint<List<String>> {
@ConfigurationProperties(prefix = "endpoints.git", ignoreUnknownFields = false)
public class GitEndpoint extends AbstractEndpoint<GitProperties> {

	@Autowired
	private GitProperties gitProperties;

	public GitEndpoint() {
		super("git");
	}

	@Override
	public String getId() {
		return "git";
	}

	//	@Override
	//	public boolean isEnabled() {
	//		return true;
	//	}
	//
	//	@Override
	//	public boolean isSensitive() {
	//		return true;
	//	}

	@Override
	public GitProperties invoke() {
		return gitProperties;
	}
}