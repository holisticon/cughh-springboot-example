package de.holisticon.bpm.cughh.externaltask.client.service;

import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamundaExternalTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(CamundaExternalTaskApplication.class, args);
  }

  @Bean
  public ResourceConfig jerseyConfig() {
    return new CamundaJerseyResourceConfig();
  }
}
