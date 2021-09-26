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
  `CUSTOMER_CONTACT` VARCHAR(10) NOT NULL,
  `CUSTOMER_PINCODE` VARCHAR(6) NOT NULL,
  `CUSTOMER_PASSWORD` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`employee` (
  `EMPLOYEE_ID` VARCHAR(10) NOT NULL,
  `EMPLOYEE_NAME` VARCHAR(30) NOT NULL,
  `EMPLOYEE_PASSWORD` VARCHAR(15) NOT NULL,
  `LAST_LOGGIN_DATETIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`orders` (
  `ORDER_ID` VARCHAR(10) NOT NULL,
  `ORDER_DATE` DATE NOT NULL,
  `TOTAL_ORDER_VALUE` FLOAT NOT NULL,
  `SHIPPING_COST` FLOAT NOT NULL,
  `SHIPPING_AGENCY` VARCHAR(45) NULL DEFAULT 'BlueDart',
  `STATUS` VARCHAR(30) NOT NULL DEFAULT 'Pending',
  `STATUS_DATE` DATE NULL DEFAULT NULL,
  `CUSTOMER_ID` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  INDEX `CUSTOMER_ID_idx` (`CUSTOMER_ID` ASC) VISIBLE,
  CONSTRAINT `CUSTOMER_ID`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `order_processing`.`customer` (`CUSTOMER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`product` (
  `PRODUCT_ID` VARCHAR(10) NOT NULL,
  `PRODUCT_NAME` VARCHAR(60) NOT NULL,
  `PRODUCT_PRICE` FLOAT NOT NULL,
  `PRODUCT_CATEGORY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`order_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`order_line` (
  `ORDERLINE_ORDER_ID` VARCHAR(10) NOT NULL,
  `ORDERLINE_CUSTOMER_ID` VARCHAR(10) NOT NULL,
  `ORDERLINE_PRODUCT_ID` VARCHAR(10) NOT NULL,
  `QUANTITY` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ORDERLINE_ORDER_ID`, `ORDERLINE_CUSTOMER_ID`, `ORDERLINE_PRODUCT_ID`),
  INDEX `CUSTOMER_ID_idx` (`ORDERLINE_CUSTOMER_ID` ASC) VISIBLE,
  INDEX `ORDERLINE_PRODUCT_ID_idx` (`ORDERLINE_PRODUCT_ID` ASC) VISIBLE,
  CONSTRAINT `ORDERLINE_CUSTOMER_ID`
    FOREIGN KEY (`ORDERLINE_CUSTOMER_ID`)
    REFERENCES `order_processing`.`customer` (`CUSTOMER_ID`),
  CONSTRAINT `ORDERLINE_ORDER_ID`
    FOREIGN KEY (`ORDERLINE_ORDER_ID`)
    REFERENCES `order_processing`.`orders` (`ORDER_ID`),
  CONSTRAINT `ORDERLINE_PRODUCT_ID`
    FOREIGN KEY (`ORDERLINE_PRODUCT_ID`)
    REFERENCES `order_processing`.`product` (`PRODUCT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`invoice` (
  `INVOICE_ID` VARCHAR(10) NOT NULL,
  `INVOICE_DATE` DATE NOT NULL,
  `TOTAL_GST_VALUE` FLOAT NOT NULL,
  `TOTAL_INVOICE_VALUE` FLOAT NOT NULL,
  `INVOICE_STATUS` VARCHAR(20) NOT NULL,
  `ORDER_ID` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  INDEX `ORDER_ID_idx` (`ORDER_ID` ASC) VISIBLE,
  CONSTRAINT `ORDER_ID`
    FOREIGN KEY (`ORDER_ID`)
    REFERENCES `order_processing`.`order_line` (`ORDERLINE_ORDER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`invoice_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`invoice_seq` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`order_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`order_seq` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `order_processing`.`product_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_processing`.`product_seq` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 75
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `order_processing`;

DELIMITER $$
USE `order_processing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `order_processing`.`tg_table1_insert`
BEFORE INSERT ON `order_processing`.`orders`
FOR EACH ROW
BEGIN
  INSERT INTO order_seq VALUES (NULL);
  SET NEW.ORDER_ID = CONCAT('ORDR', LPAD(LAST_INSERT_ID(), 3, '0'));
END$$

USE `order_processing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `order_processing`.`pg1_table1_insert`
BEFORE INSERT ON `order_processing`.`product`
FOR EACH ROW
BEGIN
  INSERT INTO product_seq VALUES (NULL);
  SET NEW.PRODUCT_ID = CONCAT('PROD', LPAD(LAST_INSERT_ID(), 3, '0'));
END$$

USE `order_processing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `order_processing`.`in_table1_insert`
BEFORE INSERT ON `order_processing`.`invoice`
FOR EACH ROW
BEGIN
  INSERT INTO invoice_seq VALUES (NULL);
  SET NEW.INVOICE_ID = CONCAT('INVC', LPAD(LAST_INSERT_ID(), 3, '0'));
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
