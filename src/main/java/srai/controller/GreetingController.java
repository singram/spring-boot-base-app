package srai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import srai.model.repository.PersonRepository;

/** Simple controller to illustrate templates. */
@Controller
public class GreetingController {

  /** Person repository. */
  @Autowired
  private transient PersonRepository repository;

  /**
   * Simple greeting API.
   * @return reference to greetings template
   */
  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public String greeting(
      @RequestParam(value = "name", required = false, defaultValue = "World") final String name,
      final Model model) {
    this.repository.findAll();
    model.addAttribute("name", name);
    return "greeting";
  }

}
