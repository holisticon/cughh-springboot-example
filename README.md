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




