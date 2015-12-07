package srai;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;

public class StepDefs extends AbstractDefs {
	@When("^I run a failing step$")
	public void i_run_a_failing_step() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}