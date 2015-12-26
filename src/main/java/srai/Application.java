package srai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Application. */
@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {

  /** Main entry point. */
  public static void main(final String... args) {
    SpringApplication.run(Application.class, args);
  }

}
