INSERT INTO `user` (`id`, `name`, `password`, `username`, `role`) VALUES (1, 'Rule Developer 1', 'pw', 'ruledev1', 0);
INSERT INTO `user` (`id`, `name`, `password`, `username`, `role`) VALUES (2, 'Rule Develeper 2', 'pw', 'ruledev2', 0);
INSERT INTO `user` (`id`, `name`, `password`, `username`, `role`) VALUES (3, 'Repository Admin', 'pw', 'repoadmin', 1);
INSERT INTO `user` (`id`, `name`, `password`, `username`, `role`) VALUES (4, 'User', 'pw', 'user', 2);
INSERT INTO `user` (`id`, `name`, `password`, `username`, `role`) VALUES (5, 'Domain Expert', 'pw', 'domainexpert', 3);

INSERT INTO `contextdb` (`name`) VALUES ('allInterests_allFlightPhases_allEventScenarios');
INSERT INTO `contextdb` (`name`) VALUES ('aircraft_allFlightPhases_obstruction');
INSERT INTO `contextdb` (`name`) VALUES ('helicopter_allFlightPhases_obstruction');
INSERT INTO `contextdb` (`name`) VALUES ('aircraft_onground_closure');
INSERT INTO `contextdb` (`name`) VALUES ('landplane_onground_runwayClosure');
INSERT INTO `contextdb` (`name`) VALUES ('landplane_allFlightPhases_specialPort');
INSERT INTO `contextdb` (`name`) VALUES ('landplane_allFlightPhases_aerodromeBeaconStatus');
INSERT INTO `contextdb` (`name`) VALUES ('aircraft_onground_aerodromeEquipment');
INSERT INTO `contextdb` (`name`) VALUES ('aircraft_onground_allEventScenarios');
INSERT INTO `contextdb` (`name`) VALUES ('aircraft_allFlightPhases_closure');

INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('allInterests_allFlightPhases_allEventScenarios', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('aircraft_allFlightPhases_obstruction', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('helicopter_allFlightPhases_obstruction', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('aircraft_onground_closure', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('landplane_onground_runwayClosure', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('landplane_allFlightPhases_specialPort', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('landplane_allFlightPhases_aerodromeBeaconStatus', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('aircraft_onground_aerodromeEquipment', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('aircraft_onground_allEventScenarios', 1);
INSERT INTO `contextdb_rule_developers` (`contexts_name`, `rule_developers_id`) VALUES ('aircraft_allFlightPhases_closure', 1);
