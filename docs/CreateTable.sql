REM ********************************************************************
PROMPT Removing 'car' database object
REM ********************************************************************

DROP TABLE IF EXISTS `regis`.`cars`;


REM ********************************************************************
PROMPT Creating relational table 'CAR' that will hold the cars in the 
PROMPT Fleet inventory
REM ********************************************************************


CREATE TABLE `regis`.CARS (
  idCAR INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  LOCATION_FK INTEGER UNSIGNED NOT NULL,
  engine_type VARCHAR(20) NULL,
  rate FLOAT NULL,
  manufacturer VARCHAR(40) NULL,
  model VARCHAR(20) NULL,
  miles_included VARCHAR(20) NULL,
  rented CHAR NULL,
  PRIMARY KEY(idCAR),
  INDEX CARS_FKIndex1(LOCATION_FK),
  FOREIGN KEY(LOCATION_FK)
    REFERENCES LOCATION(idLOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

REM ********************************************************************
PROMPT Inserting sample data into relational table 'CAR' ...
REM ********************************************************************

INSERT INTO regis.CARS (idCAR, LOCATION_FK, rate, manufacturer, model, miles_included, rented) VALUES(
1, 1, 23.50, "Hyundai", "Accent", "Unlimited", "N"); 

INSERT INTO regis.CARS (idCAR, LOCATION_FK, rate, manufacturer, model, miles_included, rented) VALUES(
2, 1, 25.50, "Ford", "Focus", "Unlimited", "N"); 

INSERT INTO regis.CARS (idCAR, LOCATION_FK, rate, manufacturer, model, miles_included, rented) VALUES(
3, 1, 26.50, "Toyota", "Corolla", "Unlimited", "N"); 


REM ********************************************************************
PROMPT Removing 'location' database object
REM ********************************************************************

DROP TABLE IF EXISTS `regis`.`location`;

REM ********************************************************************
PROMPT Creating relational table 'LOCATION ' that will hold the locations where the 
PROMPT cars are located in Fleet inventory
REM ********************************************************************

CREATE TABLE  `regis`.`location` (
  `idLOCATION` int(10) unsigned NOT NULL auto_increment,
  `CITY` varchar(20) default NULL,
  PRIMARY KEY  (`idLOCATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


REM ********************************************************************
PROMPT Inserting sample data into relational table 'LOCATION' ...
REM ********************************************************************

INSERT INTO regis.LOCATION (idLocation, city) VALUES(
1, "San Francisco"); 