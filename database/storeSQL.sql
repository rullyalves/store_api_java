
CREATE SCHEMA IF NOT EXISTS `store` DEFAULT CHARACTER SET utf8 ;
USE `store` ;

-- -----------------------------------------------------
-- Table `store`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `category_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `category_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`subcategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`subcategories` (
  `subcategory_id` INT NOT NULL AUTO_INCREMENT,
  `subcategory_name` VARCHAR(45) NOT NULL,
  `subcategory_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `subcategory_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `subcategory_category_id` INT NOT NULL,
  PRIMARY KEY (`subcategory_id`, `subcategory_category_id`),
  CONSTRAINT `fk_subcategories_categories`
    FOREIGN KEY (`subcategory_category_id`)
    REFERENCES `store`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `store`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `product_description` VARCHAR(500) NOT NULL,
  `product_price` DECIMAL(10,2) NOT NULL,
  `product_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `product_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `product_subcategory_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `product_subcategory_id`),
  CONSTRAINT `fk_products_subcategories1`
    FOREIGN KEY (`product_subcategory_id`)
    REFERENCES `store`.`subcategories` (`subcategory_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `user_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`sexes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`sexes` (
  `sex_id` INT NOT NULL AUTO_INCREMENT,
  `sex_name` VARCHAR(45) NOT NULL,
  `sex_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `sex_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sex_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`user_profile` (
  `user_profile_id` INT NOT NULL AUTO_INCREMENT,
  `user_profile_name` VARCHAR(45) NOT NULL,
  `user_profile_lastname` VARCHAR(100) NOT NULL,
  `user_profile_birthday` DATE NOT NULL,
  `user_profile_cpf` VARCHAR(13) NOT NULL,
  `user_profile_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `user_profile_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_profile_user_id` INT NOT NULL,
  `user_profile_sex_id` INT NOT NULL,
  PRIMARY KEY (`user_profile_id`, `user_profile_user_id`, `user_profile_sex_id`),
  CONSTRAINT `fk_user_profile_users1`
    FOREIGN KEY (`user_profile_user_id`)
    REFERENCES `store`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile_sexes1`
    FOREIGN KEY (`user_profile_sex_id`)
    REFERENCES `store`.`sexes` (`sex_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`groups` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(45) NOT NULL,
  `group_description` VARCHAR(45) NOT NULL,
  `group_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `group_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `store`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  `role_description` VARCHAR(45) NOT NULL,
  `role_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `role_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`group_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`group_roles` (
  `group_role_id` INT NOT NULL AUTO_INCREMENT,
  `group_role_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `group_role_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `group_role_group_id` INT NOT NULL,
  `group_role_role_id` INT NOT NULL,
  PRIMARY KEY (`group_role_id`, `group_role_group_id`, `group_role_role_id`),
  CONSTRAINT `fk_group_roles_groups1`
    FOREIGN KEY (`group_role_group_id`)
    REFERENCES `store`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_roles_roles1`
    FOREIGN KEY (`group_role_role_id`)
    REFERENCES `store`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`user_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`user_groups` (
  `user_group_id` INT NOT NULL AUTO_INCREMENT,
  `user_group_user_id` INT NOT NULL,
  `user_group_group_id` INT NOT NULL,
  `user_group_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `user_group_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_group_id`, `user_group_user_id`, `user_group_group_id`),
  CONSTRAINT `fk_users_has_groups_users1`
    FOREIGN KEY (`user_group_user_id`)
    REFERENCES `store`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_groups_groups1`
    FOREIGN KEY (`user_group_group_id`)
    REFERENCES `store`.`groups` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `order_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `order_user_profile_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `order_user_profile_id`),
  CONSTRAINT `fk_orders_user_profile1`
    FOREIGN KEY (`order_user_profile_id`)
    REFERENCES `store`.`user_profile` (`user_profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `store`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`order_items` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `order_item_order_id` INT NOT NULL,
  `order_item_product_id` INT NOT NULL,
  `order_item_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `order_item_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `order_item_amount` INT NOT NULL,
  PRIMARY KEY (`order_item_id`, `order_item_order_id`, `order_item_product_id`),
  CONSTRAINT `fk_orders_has_products_orders1`
    FOREIGN KEY (`order_item_order_id`)
    REFERENCES `store`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_has_products_products1`
    FOREIGN KEY (`order_item_product_id`)
    REFERENCES `store`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`user_favorites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`user_favorites` (
  `user_favorite_id` INT NOT NULL AUTO_INCREMENT,
  `user_favorite_user_profile_id` INT NOT NULL,
  `user_favorite_product_id` INT NOT NULL,
  `user_favorite_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `user_favorite_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_favorite_id`, `user_favorite_user_profile_id`, `user_favorite_product_id`),
  CONSTRAINT `fk_user_profile_has_products_user_profile1`
    FOREIGN KEY (`user_favorite_user_profile_id`)
    REFERENCES `store`.`user_profile` (`user_profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile_has_products_products1`
    FOREIGN KEY (`user_favorite_product_id`)
    REFERENCES `store`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`regions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`regions` (
  `region_id` INT NOT NULL AUTO_INCREMENT,
  `region_name` VARCHAR(45) NOT NULL,
  `region_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `region_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`region_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`states`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`states` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `state_name` VARCHAR(45) NOT NULL,
  `state_symbol` VARCHAR(45) NOT NULL,
  `state_region_id` INT NOT NULL,
  `state_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `state_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`state_id`, `state_region_id`),
  CONSTRAINT `fk_states_regions1`
    FOREIGN KEY (`state_region_id`)
    REFERENCES `store`.`regions` (`region_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`cities` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(45) NOT NULL,
  `city_state_id` INT NOT NULL,
  `city_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `city_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`city_id`, `city_state_id`),
  CONSTRAINT `fk_cities_states1`
    FOREIGN KEY (`city_state_id`)
    REFERENCES `store`.`states` (`state_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `store`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address_zipcode` VARCHAR(20) NULL,
  `address_street` VARCHAR(60) NULL,
  `address_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `address_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `address_city_id` INT NOT NULL,
  `address_user_profile_id` INT NOT NULL,
  PRIMARY KEY (`address_id`, `address_city_id`, `address_user_profile_id`),
  CONSTRAINT `fk_addresses_cities1`
    FOREIGN KEY (`address_city_id`)
    REFERENCES `store`.`cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_addresses_user_profile1`
    FOREIGN KEY (`address_user_profile_id`)
    REFERENCES `store`.`user_profile` (`user_profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


