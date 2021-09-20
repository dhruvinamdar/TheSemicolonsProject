-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema order_processing
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema order_processing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `order_processing` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `order_processing` ;

-- -----------------------------------------------------
-- Table `order_processing`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`company` (
  `COMPANY_ID` VARCHAR(10) NOT NULL,
  `COMPANY_NAME` VARCHAR(50) NOT NULL,
  `COMPANY_ADDRESS_LINE1` VARCHAR(40) NOT NULL,
  `COMPANY_ADDRESS_CITY` VARCHAR(45) NOT NULL,
  `COMPANY_ADDRESS_STATE` VARCHAR(45) NOT NULL,
  `COMPANY_GST_NUMBER` VARCHAR(15) NOT NULL,
  `COMPANY_PINCODE` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`customer` (
  `CUSTOMER_ID` VARCHAR(10) NOT NULL,
  `CUSTOMER_NAME` VARCHAR(30) NOT NULL,
  `CUSTOMER_GST` VARCHAR(15) NOT NULL,
  `CUSTOMER_ADDRESS_LINE1` VARCHAR(40) NOT NULL,
  `CUSTOMER_ADDRESS_CITY` VARCHAR(45) NOT NULL,
  `CUSTOMER_ADDRESS_STATE` VARCHAR(45) NOT NULL,
  `CUSTOMER_EMAIL` VARCHAR(30) NOT NULL,
  `CUSTOMER_CONTACT` LONGTEXT NOT NULL,
  `CUSTOMER_PINCODE` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`employee` (
  `EMPLOYEE_ID` INT NOT NULL,
  `EMPLOYEE_NAME` VARCHAR(30) NOT NULL,
  `EMPLOYEE_PASSWORD` VARCHAR(15) NOT NULL,
  `LAST_LOGGIN_DATETIME` DATETIME NOT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`invoice` (
  `INVOICE_ID` VARCHAR(10) NOT NULL,
  `INVOICE_DATE` DATE NOT NULL,
  `TOTAL_GST_VALUE` INT NOT NULL,
  `TOATL_INVOICE_VALUE` INT NOT NULL,
  `INVOICE_STATUS` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`INVOICE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`order` (
  `ORDER_ID` VARCHAR(10) NOT NULL,
  `ORDER_DATE` DATE NOT NULL,
  PRIMARY KEY (`ORDER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`order_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`order_line` (
  `ORDER_ID` VARCHAR(10) NOT NULL,
  `CUSTOMER_SHIPPING_ADDRESS_LINE1` VARCHAR(40) NOT NULL,
  `CUSTOMER_SHIPPING_ADDRESS_CITY` VARCHAR(45) NULL DEFAULT NULL,
  `CUSTOMER_SHIPPING_ADDRESS_STATE` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  CONSTRAINT `CUSTOMER_ID`
    FOREIGN KEY (`ORDER_ID`)
    REFERENCES `order_processing`.`customer` (`CUSTOMER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`product` (
  `PRODUCT_ID` INT NOT NULL,
  `PRODUCT_NAME` VARCHAR(20) NOT NULL,
  `PRODUCT_PRICE` INT NOT NULL,
  `PRODUCT_CATEGORY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`product_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`product_line` (
  `PRODUCT_ID` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`),
  CONSTRAINT `INVOICE_ID`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `order_processing`.`invoice` (`INVOICE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
