package srai.actuator.properties;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:git.properties")
public class GitProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(GitProperties.class);

	private String gitId;

	private String userName;

	private Date time;

	private String message;

	@Autowired
	public GitProperties(
			@Value("${git.commit.id.abbrev}") String gitId,
			@Value("${git.commit.user.name}") String userName,
			@Value("${git.commit.message.full}") String message,
			@Value("${git.commit.time}") String time
			) {
		this.gitId = gitId;
		this.userName = userName;
		this.message = message;
		this.setTime(time);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.gitId)
				.append("user", this.userName)
				.append("message", this.message)
				.append("time", this.getTime().toString())
				.toString();
	}

	@PostConstruct
	public void writeGitCommitInformationToLog() {
		LOGGER.info("Application was built by using the Git commit: {}", this);
	}

	public String getGitId() {
		return gitId;
	}

	public void setGitId(String gitId) {
		this.gitId = gitId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(String timeSeconds) {
		this.setTime(Integer.parseInt(timeSeconds)*1000L);
	}

	public void setTime(long timeMS) {
		this.time = new Date(timeMS);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}