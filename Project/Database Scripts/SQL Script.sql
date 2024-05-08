DROP DATABASE IF EXISTS `online-vegetable-sales`;
CREATE DATABASE `online-vegetable-sales`;
USE `online-vegetable-sales`;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `vegetable`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `contact_us`;
DROP TABLE IF EXISTS `feedback`;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `online-vegetable-sales`.`role` (`name`)
VALUES
("ROLE_ADMIN"),
("ROLE_CUSTOMER");

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `online-vegetable-sales`.`category` (`category_name`)
VALUES
("Leafy Green"),
("Cruciferous"),
("Marrow"),
("Root"),
("Edible Plant Stem"),
("Allium");

CREATE TABLE `vegetable` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sku` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `units_in_stock` int DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated` datetime DEFAULT CURRENT_TIMESTAMP,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `online-vegetable-sales`.`vegetable`(`sku`,`name`,`description`,`unit_price`,`image_url`,`active`,`units_in_stock`,`category_id`)
VALUES
("RT01","Potato","Potato, annual plant in the nightshade family, grown for its starchy edible tubers.",34,"/img/vegetable/root/potato.jpg",1,50,4),
("RT02","Onion","An onion also known as the bulb onion or common onion, is a vegetable that is the most widely cultivated species of the genus Allium.",80,"/img/vegetables/root/onion.jpg",1,100,4),
("AL01","Garlic","Garlic is a well-known herb that is used as a spice or condiment in many cuisines, including Asian, African, European, and Mediterranean. It contains enzymes, vitamin B, flavonoids, minerals, antioxidants, and protein.",155,"/img/vegetables/allium/garlic.jpg",1,120,6),
("AL02","Leek","Leeks are a good source of fiber, including prebiotics that help feed the good bacteria in the gut. Leeks are also a good source of allicin, a compound that may help prevent the spread of cancer cells.",200,"/img/vegetables/allium/leek.jpg",1,50,6),
("CR01","Radish","Radishes are eaten all over the world, usually raw in salads, but can also be cooked as vegetables. They have anti-cancer, anti-diabetic, anti-hypertensive, and anti-obesity effects.",30,"/img/vegetables/cruciferous/radish.jpg",1,200,2),
("CR02","Turnip","Turnips can be consumed raw or cooked in a variety of ways, including: Roasts, Stews, Soups, Casseroles, Boiled, Sliced in salads, Pickled, and Mashed.Turnips are a good source of vitamin C, folate, iron, and calcium.",55,"/img/vegetables/cruciferous/turnip.jpg",1,20,2),
("ES01","Bamboo Shoot","Bamboo shoots are the edible shoots of bamboo plants that are new culms that emerge from the ground.Bamboo shoots are rich contents of proteins, carbohydrates, vitamins, fibers, and minerals and very low fat.",200,"/img/vegetables/ediblePlantStem/bambooShoot.jpg",1,10,5),
("ES02","Carrot","Carrots are high in fiber, vitamin A, vitamin C, and vitamin K, and contain antioxidants and nutrients that promote healthy skin and fight disease. Carrots can be used in salads, relishes, stews, soups, and cooked vegetables.",50,"/img/vegetables/ediblePlantStem/carrot.jpg",1,80,5),
("LG01","Cabbage","Cabbages are rich in vitamin C, fiber, and vitamin K. They may help with heart and digestive health.",50,"/img/vegetables/leafyGreen/cabbage.jpg",1,35,1),
("LG02","Spinach","Spinach is a rich source of vitamins A and C, as well as iron, magnesium, potassium, and calcium. It can be eaten fresh or processed, and is often served as a salad green or cooked in soups, soufflés, mousses, and other dishes.",90,"/img/vegetables/leafyGreen/spinach.jpg",1,40,1),
("MW01","Cucumber","Cucumbers are long, green, cylindrical fruits with a hard, warty skin and wet, transparent flesh that are usually eaten raw in salads or sandwiches. They are also used as cooked vegetables or preserved as pickles or marinated. ",40,"/img/vegetables/marrow/cucumber.jpg",1,70,3),
("MW02","Zucchini","Zucchini is rich in water and fiber, which can help with digestion and reduce the risk of constipation. It may also help lower blood sugar levels in people with type 2 diabetes.",100,"/img/vegetables/marrow/zucchini.jpg",1,15,3);

CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `vegetable_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_c_customer_id` (`user_id`),
  KEY `K_c_vegetable_id` (`vegetable_id`),
  CONSTRAINT `FK_c_customer_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_c_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  -- `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- KEY `FK_address_order_id` (`order_id`),
--   CONSTRAINT `FK_address_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total_price` decimal(10,0) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `billing_address_id` bigint DEFAULT NULL,
  `shipping_address_id` bigint DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint DEFAULT NULL,
  `delivery_charge` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_billing_address_id` (`billing_address_id`),
  UNIQUE KEY `UK_shipping_address_id` (`shipping_address_id`),
  KEY `K_user_id` (`user_id`),
  CONSTRAINT `FK_billing_address_id` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `vegetable_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  KEY `FK_vegetable_id` (`vegetable_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) DEFAULT NULL,
  `vegetable_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `date_posted` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `K_vegetable_id` (`vegetable_id`),
  KEY `FK7k33yw505d347mw3avr93akao` (`user_id`),
  CONSTRAINT `FK7k33yw505d347mw3avr93akao` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_f_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `contact_us` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `date_posted` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_contact_user_id` (`user_id`),
  CONSTRAINT `FK_contact_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
