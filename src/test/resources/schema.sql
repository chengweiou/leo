DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) NOT NULL,
  `token` varchar(500) NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
)