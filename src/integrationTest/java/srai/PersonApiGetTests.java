package srai.integration;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import srai.Application;

import javax.servlet.http.HttpServletResponse;

/** Person restful API integration tests. */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners(
    {DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class}
    )
@IntegrationTest("server.port:0")
@WebAppConfiguration
@FlywayTest(locationsForMigrate = {"db/data"})
@Transactional
@SuppressWarnings(
    { "PMD.JUnitTestsShouldIncludeAssert", "PMD.CommentRequired" })
public class PersonApiGetTests {

  @Autowired
  private transient WebApplicationContext context;

  @Value("${local.server.port}")
  private transient int port;

  /** API endpoint to retrieve posted data. */
  private static final String RETRIEVE_ENDPOINT = "/person/{personId}";

  @Before
  public void setUp() {
    RestAssured.port = port;
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  @SuppressWarnings({"checkstyle:Indentation"})
  @Test
  public void getPersonRecordFixtureSuccess() {
    given()
    .log().all(true)
    .when()
    .get(RETRIEVE_ENDPOINT, 1)
    .then()
    .log().all(true)
    .contentType(ContentType.JSON)
    .statusCode(HttpServletResponse.SC_OK)
      .body("firstName", equalTo("Stuart"));
  }

  @Test
  public void getPersonRecordFixtureMissing() {
    given()
    .when()
    .get(RETRIEVE_ENDPOINT, 0)
    .then()
      .statusCode(HttpServletResponse.SC_NOT_FOUND);
  }

}
