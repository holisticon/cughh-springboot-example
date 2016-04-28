# Twitter Process 

## 1 springify example camunda twitter process

* based on https://github.com/camunda/camunda-consulting/tree/master/showcases/twitter
* named delegates to spring component
* jsf forms removed.

## 2 twitter service

* introduce twitter-service module
* move updateStatus from delegate to twitterService#sentTweet

## 3 twitter process spring boot application

* move webapp/forms to resources/static/forms
* pom.xml
** mysql connector
** camunda-spring-boot-starter-webapp
* TwitterProcessApplication
* application.yaml

```
camunda.bpm:
  metrics.enabled: false
  history-level: auto
  database:
    schema-update: false
    type: mysql

spring.datasource:
  url: jdbc:mysql://localhost:3306/CUGHH?useSSL=false
  username: camunda
  password: camunda
  driver-class-name: com.mysql.jdbc.Driver

server:
  port: 8081
```

## 4 use dmn ruletask

* twitter-rule module
** dependency groovy-all
* META-INF/processes.xml
* twitter-process
* extend process bpmn
** ruleTask - dmn
* include rule dependency in pom

## 5 prepare hytrix rule call

### twitter-rule

* pom dependency - starter-rest and h2
* becomes camunda spring rest application
```
  public static void main(String[] args) {
    SpringApplication.run(TwitterRuleApplication.class, args);
  }

  @Bean
  public ResourceConfig jerseyConfig() {
    return new CamundaJerseyResourceConfig();
  }
```

test via: 

http://localhost:8082/rest/engine/default/decision-definition/

### twitter process

* remove dep twitter-rule
 
* new delegate ApproveAuthorDelegate 
* adjust bpmn
* add dependency hystrix core
* url in application.yaml

dump mysql !





