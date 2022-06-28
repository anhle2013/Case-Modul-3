CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_more_quantity`(
	IN bookId INT,
    IN amount INT
)
BEGIN
	UPDATE books AS b
	SET 
		b.quantity = b.quantity + amount,
        b.available = b.available + amount
	WHERE b.id = bookId;
END