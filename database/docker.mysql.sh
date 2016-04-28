#!/bin/bash -e


docker run --name camunda-mysql \
  -ti \
  --rm \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=CUGHH \
  -e MYSQL_USER=camunda \
  -e MYSQL_PASSWORD=camunda \
  -v $PWD/sql:/docker-entrypoint-initdb.d  \
  -p 3306:3306  \
  mysql:latest
