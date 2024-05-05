USE `online-vegetable-sales`;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `feedback`;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `feedback` (
`id` bigint NOT NULL AUTO_INCREMENT,
`feedback` VARCHAR(255) DEFAULT NULL,
`customer_id` bigint DEFAULT NULL,
`vegetable_id` bigint DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `K_customer_id` (`customer_id`),
KEY `K_vegetable_id` (`vegetable_id`),
CONSTRAINT `FK_f_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
CONSTRAINT `FK_f_vegetable_id` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;