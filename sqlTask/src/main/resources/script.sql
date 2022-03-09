CREATE DATABASE  IF NOT EXISTS `medrecord`;
USE `medrecord`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `visit`;
DROP TABLE IF EXISTS `health`;
DROP TABLE IF EXISTS `ticket`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `health` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `patient` int(11) DEFAULT NULL,
                          `photo` varchar(500) DEFAULT NULL,
                          `birth` date DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `patient_id_UNIQUE` (`patient`),
                          CONSTRAINT `fk_health_patient.id` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
                          `id` int(11) NOT NULL,
                          `patient` int(11) DEFAULT NULL,
                          `doctor` int(11) DEFAULT NULL,
                          `datetime` datetime DEFAULT NULL,
                          `attendance` tinyint(4) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_ticket_patient.id_idx` (`patient`),
                          KEY `fk_ticket_doctor.id_idx` (`doctor`),
                          CONSTRAINT `fk_ticket_doctor.id` FOREIGN KEY (`doctor`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                          CONSTRAINT `fk_ticket_patient.id` FOREIGN KEY (`patient`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `visit` (
                         `id` int(11) NOT NULL,
                         `ticket` int(11) DEFAULT NULL,
                         `patient` int(11) DEFAULT NULL,
                         `doctor` int(11) DEFAULT NULL,
                         `datetime` datetime DEFAULT NULL,
                         `complaint` text,
                         `examination` text,
                         `diagnosis` text,
                         `treatment` text,
                         PRIMARY KEY (`id`),
                         KEY `fk_visit_patient.id_idx` (`patient`),
                         KEY `fk_visit_doctor.id_idx` (`doctor`),
                         CONSTRAINT `fk_visit_doctor.id` FOREIGN KEY (`doctor`) REFERENCES `user` (`id`),
                         CONSTRAINT `fk_visit_patient.id` FOREIGN KEY (`patient`) REFERENCES `user` (`id`),
                         CONSTRAINT `fk_visit_ticket.id` FOREIGN KEY (`id`) REFERENCES `ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
                             `user_id` int(11) NOT NULL,
                             `role_id` int(11) NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             KEY `fk_user_role_roleid_idx` (`role_id`),
                             CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_PATIENT'),(20,'ROLE_MEDIC'),(21,'ROLE_EXAMPLE1444444');
INSERT INTO `user` VALUES (4,'Username','$2a$11$g9Zb/tf8UaqKqsMw6X6Gieh1BidOmCVHL7RUadRIyoJ/Af9wTEexC'),(5,'NewUsername','$2a$11$FNmAIsmKFjQLmKBCDVpg0exbDysE0fxRNHNSsdQ833LsnMIzXzeFm'),(6,'NewName','$2a$11$yI75hVAkWH9DMGTy2Q9Ig.BeADcIXjZELRRmORQ09ZfWHf7AeHi3.'),(7,'123456','$2a$11$DQvk17GQ3PKNCfVmAD5/reCQYYvaPwQMCo99L4v96lt6TL2f6yll2'),(8,'654321','$2a$11$kYv3KyqXFuduklYC1cezV.sdPmPELjbIuOI7piB7kf9SHTZ9R6BeC'),(9,'12345678','$2a$11$2Xx1iFBPtdcrJNFVCZk0PubC2b8.hqgjAP87DGTg.BXGikVeB28cm'),(11,'11111111','$2a$11$jL1Yu.G1Y3JT3uqbfSHZXOw4RdKq4Ux4FmMFvq3CtBiJ7W2LfNnJC'),(16,'87654321','$2a$11$AM2B2y5x5n.zW552LFf46uJV/0iVM9z7fMo8kheyYY3egPRmANkW.'),(18,'FirstCreateUser','$2a$11$EDETgZyKXT7moOzC5Hn/IOUq7I4Pglkk05l2Hi7XZeaDp8xH0y0qS'),(19,'12312312','$2a$11$iiVBgRdqfdZYXJIl8MimoOebKfi.YaQXvYDWkIZC.OddzUdr.SQ0a');
INSERT INTO `user_role` VALUES (4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(11,1),(16,1),(18,1),(19,1),(8,2),(9,2),(11,2),(16,2),(18,2),(19,2),(8,3),(9,3),(11,3),(16,3),(18,3),(19,3),(16,20),(18,20),(19,20),(16,21),(18,21),(19,21);