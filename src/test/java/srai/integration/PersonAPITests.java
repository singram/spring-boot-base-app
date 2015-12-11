package srai.integration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;
import com.jayway.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
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

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	FlywayTestExecutionListener.class })
@IntegrationTest("server.port:0")
@WebAppConfiguration
@FlywayTest(locationsForMigrate = {"db/data"})
@Transactional
public class PersonAPITests {

	@Autowired
	private WebApplicationContext context;

	@Value("${local.server.port}")
	private int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssuredMockMvc.webAppContextSetup(context);
	}

	@Test
	public void readPersonRecordFixtureSuccess() {
		// @formatter:off
		given().
			log().all(true).
		when().
			get("/people/{person_id}", 1).
		then().
			log().all(true).
			contentType(ContentType.JSON).
			statusCode(HttpServletResponse.SC_OK).
			body("firstName", equalTo("Stuart"));
		// @formatter:on
	}

	@Test
	public void createPersonRecordValidationFailure() {
		// @formatter:off
		given().
			contentType(ContentType.JSON).
			body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"\" }").
		when().
			post("/people").
		then().
			contentType(ContentType.JSON).
			statusCode(HttpServletResponse.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void createPersonRecordSuccess() {
		MockMvcResponse response =
		// @formatter:off
			given().
				log().all(true).
				contentType(ContentType.JSON).
				body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }").
			when().
				post("/people").
			then().
				log().all(true).
				contentType(ContentType.JSON).
				statusCode(HttpServletResponse.SC_CREATED).
				extract().response();
		// @formatter:on

		int personId = response.path("id");
		assertThat(personId, is(2));
//				path("id");
//						response();
//				String r = response.prettyPrint();
//				String b = response.body().asString();
//				givenExistingPerson(personId).
//				and().
//				body("firstName", equalTo("Stuart"));
	}

	private ValidatableMockMvcResponse givenExistingPerson(String person_id) {
		return given().
				when().
				get("/people/{person_id}", 1).
				then().
				statusCode(200);
	}
}
