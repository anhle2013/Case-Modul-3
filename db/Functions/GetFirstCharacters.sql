CREATE DEFINER=`root`@`localhost` FUNCTION `GetFirstCharacters`(input VARCHAR(255)) RETURNS varchar(255) CHARSET utf8mb4
    READS SQL DATA
    DETERMINISTIC
BEGIN
	DECLARE len INT;
	DECLARE i INT;
    DECLARE string_out VARCHAR(255) default '';

	SET len   = CHAR_LENGTH(input);
	SET input = LOWER(TRIM(input));
	SET i = 0;

	WHILE (i < len) DO
		IF (MID(input,i,1) = ' ' or i = 0) THEN
			IF (i < len) THEN
				SET string_out = CONCAT(string_out, MID(input,i + 1,1));
			END IF;
		END IF;
		SET i = i + 1;
	END WHILE;

	RETURN string_out;
END