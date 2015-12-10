package srai.integration;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

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

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import srai.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	FlywayTestExecutionListener.class })
@IntegrationTest
@WebAppConfiguration
public class PersonAPITests {

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		RestAssuredMockMvc.webAppContextSetup(context);
	}

	@Test
	@FlywayTest(locationsForMigrate = {"db/data"})
	public void readPersonRecord() {
		given().
		when().
		get("/people/{person_id}", 1).
		then().
		statusCode(200).
		body("firstName", equalTo("Stuart"));
	}

	//	@Test
	//	@FlywayTest(locationsForMigrate = {"db/data"})
	//	public void createPersonRecord() {
	//		ValidatableMockMvcResponse response = given().
	//				body("{ \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }").
	//				when().
	//				post("/people").
	//				then().
	//				statusCode(201).
	//				body(equalTo("a string"));
	//
	//
	//	}
}
