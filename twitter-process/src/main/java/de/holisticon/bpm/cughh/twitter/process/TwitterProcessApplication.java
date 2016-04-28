package de.holisticon.bpm.cughh.twitter.process;

import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterProcessApplication extends SpringBootProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(TwitterProcessApplication.class, args);

  }
}
