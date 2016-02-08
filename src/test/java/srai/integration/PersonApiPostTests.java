package srai.integration;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

import javax.servlet.http.HttpServletResponse;

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

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;

import srai.Application;

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
public class PersonApiPostTests {

  @Autowired
  private transient WebApplicationContext context;

  @Value("${local.server.port}")
  private transient int port;

  /** API endpoint under test. */
  private static final String POST_ENDPOINT = "/person/";

  /** API endpoint to retrieve poste d data. */
  private static final String RETRIEVE_ENDPOINT = "/person/{personId}";

  @Before
  public void setUp() {
    RestAssured.port = port;
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  @Test
  public void createPersonRecordValidationFailure() {
    given()
    .log().all(true)
    .contentType(ContentType.JSON)
    .body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"\" }")
    .when()
    .post(POST_ENDPOINT)
    .then()
    .log().all(true)
    .contentType(ContentType.JSON)
      .statusCode(HttpServletResponse.SC_BAD_REQUEST);
  }

  @Test
  public void createPersonRecordSuccess() {
    final MockMvcResponse response =
        given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }")
        .when()
        .post(POST_ENDPOINT)
        .then()
        .log().all(true)
        .contentType(ContentType.JSON)
        .statusCode(HttpServletResponse.SC_CREATED)
        .extract().response();

    final int personId = response.path("id");

    given()
    .when()
    .get(RETRIEVE_ENDPOINT, personId)
    .then()
    .contentType(ContentType.JSON)
    .statusCode(HttpServletResponse.SC_OK)
      .body("lastName", equalTo("Baggins"));
  }

  @Test
  public void createPersonRecordSucessWithBeforeSaveModifiers() {
    final MockMvcResponse response =
        given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }")
        .when()
        .post(POST_ENDPOINT)
        .then()
        .log().all(true)
        .contentType(ContentType.JSON)
        .statusCode(HttpServletResponse.SC_CREATED)
        .extract().response();

    final int personId = response.path("id");

    given()
    .when()
    .get(RETRIEVE_ENDPOINT, personId)
    .then()
    .contentType(ContentType.JSON)
    .statusCode(HttpServletResponse.SC_OK)
    .body("firstName", equalTo("bar_Frodo"))
      .body("createdAt", notNullValue());
  }


  @Test
  public void createPersonRecordWithThoughtsSuccess() {
    final MockMvcResponse response =
        given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\", \"thoughts\" :"
        + " [{\"data\":\"My precious\"},{\"data\":\"One ring to rule them all\"}]}")
        .when()
        .post(POST_ENDPOINT)
        .then()
        .log().all(true)
        .contentType(ContentType.JSON)
        .statusCode(HttpServletResponse.SC_CREATED)
        .extract().response();

    final int personId = response.path("id");

    given()
    .when()
    .get(RETRIEVE_ENDPOINT, personId)
    .then()
    .contentType(ContentType.JSON)
    .statusCode(HttpServletResponse.SC_OK)
    .body("lastName", equalTo("Baggins"))
    .body("thoughts.size()", equalTo(2))
      .body("thoughts.data", hasItems("My precious", "One ring to rule them all"));
  }

  @Test
  public void createPersonRecordWithChildrenSuccess() {
    final MockMvcResponse response =
        given()
        .log().all(true)
        .contentType(ContentType.JSON)
        .body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\", \"children\" :"
        + " [ { \"firstName\" : \"Frodo Jr\",  \"lastName\" : \"Baggins\" } ] }")
        .when()
        .post(POST_ENDPOINT)
        .then()
        .log().all(true)
        .contentType(ContentType.JSON)
        .statusCode(HttpServletResponse.SC_CREATED)
        .extract().response();

    final int parentId = response.path("id");

    given()
    .when()
    .get(RETRIEVE_ENDPOINT, parentId)
    .then()
    .contentType(ContentType.JSON)
    .statusCode(HttpServletResponse.SC_OK)
    .body("lastName", equalTo("Baggins"))
    .body("children.size()", equalTo(1))
      .body("children[0].firstName", equalTo("bar_Frodo Jr"));
  }
}
