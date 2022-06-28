CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getBookDTO_byId`(
	IN book_id INT
)
BEGIN
	DECLARE exists_id INT;
	SELECT
		b.id, 
		b.name,
		a.name AS `Author Name`, 
		g.name AS `Genre Name`, 
		p.name AS `Publisher Name`, 
		b.quantity,
		b.available,
        b.active
	FROM books AS b 
	JOIN authors AS a 
	ON b.author_id = a.id 
	JOIN genres AS g 
	ON b.genre_id = g.id 
	JOIN publishers AS p 
	ON b.publisher_id = p.id
	WHERE b.id = book_id;
END