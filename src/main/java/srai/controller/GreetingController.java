package srai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** Simple controller to illustrate templates. */
@Controller
public class GreetingController {

  /**
   * Simple greeting API.
   * @return reference to greetings template
   */
  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public String greeting(
      @RequestParam(value = "name", required = false, defaultValue = "World") final String name,
      final Model model) {
    model.addAttribute("name", name);
    return "greeting";
  }

}
