-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `phonebook`;
CREATE SCHEMA IF NOT EXISTS `phonebook` DEFAULT CHARACTER SET utf8 ;
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phonebook`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `secondName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `username` (`username` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phonebook`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `secondName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `phoneMobile` VARCHAR(45) NOT NULL,
  `phoneHome` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phonebook`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phonebook`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `authority` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
