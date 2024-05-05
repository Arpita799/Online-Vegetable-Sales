USE `online-vegetable-sales`;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `contact_us`;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `contact_us` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `date_posted` DATETIME DEFAULT NOW(),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_contact_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;