/*
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `family_name` varchar(255) DEFAULT NULL
)*/ /*ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;*/

/*
CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL 
)
*/

BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `street_number` int(11) DEFAULT NULL
);

ALTER TABLE `address`
	ADD PRIMARY KEY (`id`);

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL,
  `family_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
);

ALTER TABLE `person`
	ADD PRIMARY KEY (`id`),
	ADD KEY `FKk7rgn6djxsv2j2bv1mvuxd4m9` (`address_id`),
	ADD CONSTRAINT `FKk7rgn6djxsv2j2bv1mvuxd4m9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);
	
COMMIT;