CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_author`(IN search CHAR(3))
BEGIN
	SELECT 
		id, 
		name 
	FROM authors
	WHERE GetFirstCharacters(name) LIKE '`search`%'; 
END