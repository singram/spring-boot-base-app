package srai.actuator.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.annotation.PostConstruct;

/** Git properties bean. */
@Component
@PropertySource("classpath:git.properties")
public class GitProperties {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(GitProperties.class);

  /** Git id. */
  private String gitId;

  /** Git author name. */
  private String userName;

  /** Git commit time. */
  private Date time;

  /** Git commit message. */
  private String message;

  /**
   * Constructor.
   * @param gitId  Abbreviated commit id
   * @param userName  Commit Author
   * @param message  Commit message
   * @param time  Commit time
   */
  @Autowired
  public GitProperties(@Value("${git.commit.id.abbrev}") final String gitId,
      @Value("${git.commit.user.name}") final String userName,
      @Value("${git.commit.message.full}") final String message,
      @Value("${git.commit.time}") final String time) {
    this.gitId = gitId;
    this.userName = userName;
    this.message = message;
    this.time = GitProperties.toDate(time);
  }

  /** String serializer. */
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", this.gitId).append("user", this.userName)
        .append("message", this.message).append("time", this.getTime().toString()).toString();
  }

  /** Log git details. */
  @PostConstruct
  public void writeGitCommitInformationToLog() {
    LOGGER.info("Application was built by using the Git commit: {}", this);
  }

  /** GitId getter. */
  public String getGitId() {
    return gitId;
  }

  /** GitId setter. */
  public void setGitId(final String gitId) {
    this.gitId = gitId;
  }

  /** UserName getter. */
  public String getUserName() {
    return userName;
  }

  /** UserName setter. */
  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /** Time getter. */
  public Date getTime() {
    return (Date) time.clone();
  }

  /** Time setter. */
  public void setTime(final String timeSeconds) {
    this.time = GitProperties.toDate(timeSeconds);
  }

  private static Date toDate(final String timeSeconds) {
    return toDate(Integer.parseInt(timeSeconds) * 1000L);
  }

  private static Date toDate(final long timeMs) {
    return new Date(timeMs);
  }

  /** Message getter. */
  public String getMessage() {
    return message;
  }

  /** Message setter. */
  public void setMessage(final String message) {
    this.message = message;
  }

}
