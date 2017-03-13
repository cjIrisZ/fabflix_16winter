CREATE TABLE IF NOT EXISTS moviedb.`movies` (
	`id` int NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(100) NOT NULL,
	`year` int NOT NULL,
	`director` VARCHAR(100) NOT NULL,
	`banner_url` VARCHAR(200) DEFAULT NULL,
	`trailer_url` VARCHAR(200) DEFAULT NULL,
	PRIMARY KEY(`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`stars`(
	`id` int NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`dob` date DEFAULT NULL,
	`photo_url` VARCHAR(200) DEFAULT NULL,
	PRIMARY KEY(`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`stars_in_movies`(
	`star_id` INT NOT NULL REFERENCES stars(id),
	`movie_id` INT NOT NULL REFERENCES movies(id)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`genres`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	PRIMARY KEY(`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`genres_in_movies`(
	`genre_id` INT NOT NULL REFERENCES genres(id),
	`movie_id` INT NOT NULL REFERENCES movies(id)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`customers`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`cc_id` VARCHAR(20) NOT NULL REFERENCES creditcards(id),
	`address` VARCHAR(200) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(20) NOT NULL,
	PRIMARY KEY(`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`sales`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`customer_id` INT NOT NULL REFERENCES customers(id),
	`movie_id` INT NOT NULL REFERENCES movies(id),
	`sale_date` date NOT NULL,
	PRIMARY KEY(`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;


CREATE TABLE IF NOT EXISTS moviedb.`creditcards` (
  `id` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `expiration` DATE NOT NULL,
  PRIMARY KEY (`id`)
) Engine = InnoDB DEFAULT CHARSET = latin1;