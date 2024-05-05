USE `online-vegetable-sales`;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `wishlist`;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `cart` (
`id` bigint NOT NULL AUTO_INCREMENT,
`user_id` bigint DEFAULT NULL,
`vegetable_id` bigint DEFAULT NULL,
`quantity` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `K_c_customer_id` (`user_id`),
KEY `K_c_vegetable_id` (`vegetable_id`),
CONSTRAINT `FK_c_customer_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
CONSTRAINT `FK_c_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `wishlist` (
`id` bigint NOT NULL AUTO_INCREMENT,
`user_id` bigint DEFAULT NULL,
`vegetable_id` bigint DEFAULT NULL,
`quantity` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `K_w_customer_id` (`user_id`),
KEY `K_w_vegetable_id` (`vegetable_id`),
CONSTRAINT `FK_w_customer_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
CONSTRAINT `FK_w_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;