DELIMITER $$

CREATE FUNCTION Add_Star(argFirstName VARCHAR(50),
						argLastName VARCHAR(50),
						argDOB DATE)
RETURNS INT
BEGIN
	DECLARE starid INT(11);
	INSERT INTO stars(first_name, last_name, dob) VALUES(argFirstName, argLastName, argDOB);
    SET starid = (SELECT max(id) FROM stars);
    RETURN starid;
END
$$

CREATE FUNCTION Add_Genre(argGenreName VARCHAR(32),
							argTitle VARCHAR(100),
							argYear INT,
							argDirector VARCHAR(100))
RETURNS INT
BEGIN
	DECLARE movieid INT(11);
    DECLARE genreid INT(11);
    SET movieid = (SELECT MAX(id) FROM movies WHERE title=argTitle AND year=argYear AND director=argDirector);
    SET genreid = (SELECT id FROM genres WHERE name=argGenreName);
    IF (genreid is NULL) THEN
		INSERT INTO genres(name) VALUES(argGenreName);
		SET genreid = (SELECT id FROM genres WHERE name=argGenreName);
    END IF;
    INSERT INTO genres_in_movies(genre_id,movie_id) VALUES(genreid,movieid);
    RETURN movieid;
END
$$

DELIMITER ;