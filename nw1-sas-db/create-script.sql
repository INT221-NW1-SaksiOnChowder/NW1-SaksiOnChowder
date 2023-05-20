-- MySQL Script generated by MySQL Workbench
-- Sat May 20 20:28:20 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SAS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SAS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SAS` DEFAULT CHARACTER SET utf8 ;
USE `SAS` ;

-- -----------------------------------------------------
-- Table `SAS`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAS`.`Categories` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(50) NOT NULL DEFAULT 'ทั่วไป',
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAS`.`Announcements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAS`.`Announcements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `announcementTitle` VARCHAR(200) NOT NULL,
  `announcementDescription` VARCHAR(10000) NOT NULL,
  `publishDate` DATETIME NULL,
  `closeDate` DATETIME NULL,
  `announcementDisplay` ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `categoryId` INT NOT NULL,
  `viewCount` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_Announcements_Categories_idx` (`categoryId` ASC) VISIBLE,
  CONSTRAINT `fk_Announcements_Categories`
    FOREIGN KEY (`categoryId`)
    REFERENCES `SAS`.`Categories` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
