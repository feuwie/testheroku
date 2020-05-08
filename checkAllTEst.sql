-- -------------------------------------------------------------
-- TablePlus 2.10(268)
--
-- https://tableplus.com/
--
-- Database: checkAll
-- Generation Time: 2020-05-07 20:09:12.1620
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `admin_order` (
  `adminorder_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `reviewed` int(11) NOT NULL,
  PRIMARY KEY (`adminorder_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `blind` (
  `blind_id` int(11) NOT NULL AUTO_INCREMENT,
  `backsite` varchar(255) DEFAULT NULL,
  `colorsite` varchar(255) DEFAULT NULL,
  `fontsize` int(11) DEFAULT NULL,
  `lineheight` float DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`blind_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_added` datetime DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `promo_id` int(11) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_img` varchar(255) DEFAULT NULL,
  `category_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `pers_info` (
  `persinfo_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `door` int(11) DEFAULT NULL,
  `doorphone` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `flat` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `ind` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`persinfo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `place_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `order_cost` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `payment_id` varchar(255) DEFAULT NULL,
  `canc_order` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `product_article` int(11) NOT NULL,
  `product_img` varchar(255) DEFAULT NULL,
  `product_price` int(11) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `product_title` varchar(255) DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `promo` (
  `promo_id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_code` varchar(255) DEFAULT NULL,
  `promo_type` varchar(255) DEFAULT NULL,
  `promo_value` int(11) NOT NULL,
  PRIMARY KEY (`promo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) NOT NULL,
  `review_text` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `review_added` datetime DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `ban` bit(1) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `wishlist` (
  `wishlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `wish_added` datetime DEFAULT NULL,
  PRIMARY KEY (`wishlist_id`)
) ENGINE=MyISAM AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `admin_order` (`adminorder_id`, `order_id`, `product_id`, `product_quantity`, `uuid`, `reviewed`) VALUES ('64', '49', '4', '5', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '0'),
('65', '50', '4', '4', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '0'),
('66', '51', '5', '1', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '1'),
('67', '51', '3', '1', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '0'),
('68', '51', '6', '1', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '0'),
('69', '51', '4', '1', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '0'),
('70', '52', '2', '20', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '1'),
('71', '54', '2', '20', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', '1');

INSERT INTO `blind` (`blind_id`, `backsite`, `colorsite`, `fontsize`, `lineheight`, `uuid`) VALUES ('1', '', '', '14', '1', '667526fd-28d0-4d7a-873b-6cb07d340c2d'),
('2', NULL, NULL, '12', '1', '05b5057f-0a40-4198-9f6d-16d6b371baf4'),
('3', NULL, NULL, '15', '1.3', 'a7281a38-c90c-4dc8-ad99-56f06189d625');

INSERT INTO `category` (`category_id`, `category_img`, `category_title`) VALUES ('1', NULL, NULL),
('2', '../../assets/react.png', 'Планшеты'),
('3', '../../assets/2020-03-03 11.23.56.jpg', 'Ловер');

INSERT INTO `place_order` (`order_id`, `comment`, `order_cost`, `order_date`, `order_status`, `uuid`, `payment_id`, `canc_order`) VALUES ('49', 'фвфывфыв', '38885', '2020-05-07 09:20:57', 'На складе', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'ch_1Gg5uWF5gEbHhd26TVVnMGJ0', '0'),
('50', 'ываываыва', '31108', '2020-05-07 09:21:57', 'Доставлен', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'ch_1Gg5vUF5gEbHhd26x0tgz8Vx', '0'),
('51', '32323', '8410', '2020-05-07 09:31:31', 'Доставлен', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'ch_1Gg64kF5gEbHhd26TKsuDJqA', '0'),
('52', 'ывывыв', '6660', '2020-05-07 10:31:14', 'Доставлен', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'ch_1Gg70WF5gEbHhd26ePykIbJO', '0'),
('54', '23232', '6660', '2020-05-07 10:37:21', 'Доставлен', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'ch_1Gg76SF5gEbHhd26Yf5mwH1L', '0');

INSERT INTO `product` (`product_id`, `parent_id`, `product_article`, `product_img`, `product_price`, `product_quantity`, `product_title`, `rating`) VALUES ('1', '2', '1', NULL, '2', '3', NULL, NULL),
('2', '2', '32131', '../../assets/react.png', '333', '10', 'iPad', '4.2'),
('3', '1', '21212', '../../assets/1.png', '200', '54', 'MacBook', NULL),
('4', '2', '1211', '../../assets/react.png', '7777', '42', 'Rulon', '3'),
('5', '1', '32111', '../../assets/1.png', '100', '54', 'iPhone 7', NULL),
('6', '2', '32134', '../../assets/react.png', '333', '24', 'Деньги', NULL),
('8', '2', '123', '../../assets/react.png', '123', '1', 'Машинка', NULL),
('9', '3', '321', NULL, '321', '132', 'Вертолет', NULL);

INSERT INTO `promo` (`promo_id`, `promo_code`, `promo_type`, `promo_value`) VALUES ('1', 'MARCH8', 'nominal', '100'),
('2', 'FEBRUARY23', 'percentage', '50');

INSERT INTO `review` (`review_id`, `rating`, `review_text`, `user_id`, `product_id`, `review_added`) VALUES ('1', '4', 'Супер я просто тащусь от этого товараСупер я просто тащусь от этого товараСупер я просто тащусь от этого товараСупер я просто тащусь от этого товара', '4', '2', '2020-05-15 11:19:45'),
('3', '2', 'Класс!', '4', '2', '2020-05-11 11:19:49'),
('5', '3', 'Класс!', '4', '2', '2020-05-11 11:19:49'),
('8', '1', 'Все бомба!', '4', '5', '2020-05-07 10:21:36'),
('9', '5', NULL, '4', '2', '2020-05-07 10:32:32'),
('10', '4', 'Ловер', '4', '2', '2020-05-07 10:38:28'),
('11', '5', 'ЛОл', '4', '2', '2020-05-07 10:41:19'),
('12', '5', 'Машина', '4', '2', '2020-05-07 10:43:57'),
('13', '5', 'sdfsdf', '4', '2', '2020-05-07 10:51:49'),
('14', '5', 'sdsd', '4', '2', '2020-05-07 10:54:34'),
('15', '5', 'dfdfdfd', '4', '2', '2020-05-07 10:55:33'),
('16', '5', 'ХОРОШО', '4', '2', '2020-05-07 10:59:16'),
('17', '5', 'КРУТО!', '4', '2', '2020-05-07 11:42:14');

INSERT INTO `user` (`userid`, `password`, `phone`, `usertype`, `uuid`, `email`, `dob`, `fullname`, `gender`, `ban`) VALUES ('4', '$2a$10$EDdcYkZZ4GrquUVrqq/R8uSASLEF/Do5kH5v/F6Rz4CKiT.3tyS1O', '+79877454204', 'customer', 'a657c9d3-c0ec-4e9b-a58d-060533d2f8ff', 'kakas789@mail.ru', NULL, 'Стас', NULL, b'0'),
('5', '$2a$10$EDdcYkZZ4GrquUVrqq/R8uSASLEF/Do5kH5v/F6Rz4CKiT.3tyS1O', '+79877454201', 'customer', 'loler', NULL, NULL, '', NULL, b'1');




/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
