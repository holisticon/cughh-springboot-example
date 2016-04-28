insert into ACT_GE_PROPERTY values ('historyLevel', '3', 1);

insert into ACT_ID_GROUP values ('camunda-admin',1, 'camunda BPM Administrators', 'SYSTEM');
insert into ACT_RU_AUTHORIZATION VALUES (201,1,1,'camunda-admin', NULL, 0, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (202,1,1,'camunda-admin', NULL, 1, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (203,1,1,'camunda-admin', NULL, 2, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (204,1,1,'camunda-admin', NULL, 3, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (205,1,1,'camunda-admin', NULL, 4, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (206,1,1,'camunda-admin', NULL, 5, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (207,1,1,'camunda-admin', NULL, 6, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (208,1,1,'camunda-admin', NULL, 7, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (209,1,1,'camunda-admin', NULL, 8, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (210,1,1,'camunda-admin', NULL, 9, '*', 2147483647);
insert into ACT_RU_AUTHORIZATION VALUES (211,1,1,'camunda-admin', NULL, 10, '*', 2147483647);

insert into ACT_ID_USER values ('demo', 1, 'Jan', 'Galinski', 'jan.galinski@holisticon.de', '{SHA}WGPZ5MvfUi6qYuB0f86xxbJJuhM=', NULL);
insert into ACT_ID_MEMBERSHIP values ('demo', 'camunda-admin');

insert into ACT_RU_FILTER values ('50a26de7-04e6-11e6-b0ef-0a0027000001', 1, 'Task', 'All tasks', NULL, '{}','{"color":"#EEEEEE","priority":0,"showUndefinedVariable":false,"refresh":false}');
