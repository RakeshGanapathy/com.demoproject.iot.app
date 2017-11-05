-- MySQL Workbench Synchronization
-- Generated: 2017-11-05 21:27
-- Model: New Model
-- Version: 2.0
-- Project: Name of the project
-- Author: Sasi

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

INSERT INTO `iot_app_db`.`sensors` (`name`, `type`) VALUES ('Sensor 1 ', 'temperature');
INSERT INTO `iot_app_db`.`sensors` (`name`, `type`) VALUES ('Sensor 2', 'ambient');
INSERT INTO `iot_app_db`.`sensors` (`name`, `type`) VALUES ('Sensor 3', 'pollution');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
