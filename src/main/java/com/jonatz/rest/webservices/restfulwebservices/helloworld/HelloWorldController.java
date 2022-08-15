package com.jonatz.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  /*
   * This is used by Spring to pickup properties from message bundles.
   * Springboot automatically make it work if you have a message.properties file
   * */
  @Autowired private MessageSource messageSource;

  @GetMapping(path = "/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World form Bean");
  }

  @GetMapping(path = "/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }

  @GetMapping(path = "/good-morning-internationalized")
  public String goodMorningInternationalized(
      // Instead of telling each method to use locale
      // @RequestHeader(name = "Accept-Language", required = false) Locale locale
      ) {

    //    return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    return messageSource.getMessage(
        "good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
  }
}
