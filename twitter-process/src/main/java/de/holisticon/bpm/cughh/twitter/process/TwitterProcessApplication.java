package de.holisticon.bpm.cughh.twitter.process;

import de.holisticon.bpm.cughh.twitter.service.TwitterConfiguration;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({TwitterConfiguration.class})
public class TwitterProcessApplication extends SpringBootProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(TwitterProcessApplication.class, args);
  }
}
