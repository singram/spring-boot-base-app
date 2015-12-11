package srai.integration;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import javax.servlet.http.HttpServletResponse;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.response.ValidatableMockMvcResponse;

import srai.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	FlywayTestExecutionListener.class })
@IntegrationTest
@WebAppConfiguration
@FlywayTest(locationsForMigrate = {"db/data"})
public class PersonAPITests {

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		RestAssuredMockMvc.webAppContextSetup(context);
	}

	@Test
	public void readPersonRecordFixtureSuccess() {
		given().
		when().
		get("/people/{person_id}", 1).
		then().
		contentType(ContentType.JSON).
		statusCode(HttpServletResponse.SC_OK).
		body("firstName", equalTo("Stuart"));
	}

	@Test
	public void createPersonRecordValidationFailure() {
		given().
		contentType(ContentType.JSON).
		body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"\" }").
		when().
		post("/people").
		then().
		contentType(ContentType.JSON).
		statusCode(HttpServletResponse.SC_BAD_REQUEST);
	}

	@Test
	public void createPersonRecordSuccess() {
		//MockMvcResponse response =

		//		String personId =
		given().
		contentType(ContentType.JSON).
		body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }").
		when().
		post("/people").
		then().
		contentType(ContentType.JSON).
		statusCode(HttpServletResponse.SC_CREATED);
		//				extract().
		//				path("id");
		//				response();
		//		String r = response.prettyPrint();
		//		String b = response.body().asString();
		//		String personId = response.path("id");
		//		givenExistingPerson(personId).
		//		and().
		//		body("firstName", equalTo("Stuart"));
	}

	private ValidatableMockMvcResponse givenExistingPerson(String person_id) {
		return given().
				when().
				get("/people/{person_id}", 1).
				then().
				statusCode(200);
	}
}
