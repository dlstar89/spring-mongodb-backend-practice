/*
INSERT INTO `person` (`id`,`name`,`family_name`) 
	VALUES
		(1,'Alex','Makauski'),
		(2,'Bob','Pedrero');
		
ALTER TABLE `person`
	ADD PRIMARY KEY (`id`);

*/
BEGIN TRANSACTION;

/*ADDRESS*/
INSERT INTO `address` (`id`, `street_name`, `street_number`)
	VALUES
		(1, 'Bulvar boom', 20),
		(2, 'AAA', 301);

		
/*PERSON*/
INSERT INTO `person` (`id`, `family_name`, `name`, `address_id`) 
	VALUES
		(1, 'Bobovich', 'Bob', 1),
		(2, 'Reno', 'Alex', 2);
	
COMMIT;