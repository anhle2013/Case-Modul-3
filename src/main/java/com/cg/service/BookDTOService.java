package com.cg.service;

import com.cg.dto.BookDTO;
import com.cg.model.Author;
import com.cg.model.Book;
import com.cg.utils.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDTOService implements IBookDTOService {
    private static String SELECT_ALL_BOOKS_DTO =
            "SELECT " +
                "b.id, " +
                "b.name, " +
                "a.name AS `Author Name`, " +
                "g.name AS `Genre Name`, " +
                "p.name AS `Publisher Name`, " +
                "b.quantity, " +
                "b.available " +
            "FROM books AS b " +
            "JOIN authors AS a " +
            "ON b.author_id = a.id " +
            "JOIN genres AS g " +
            "ON b.genre_id = g.id " +
            "JOIN publishers AS p " +
            "ON b.publisher_id = p.id " +
            "WHERE b.active = '1' " +
            "ORDER BY b.id;";

    private static String SEARCH_BOOKS_DTO =
            "SELECT " +
                "b.id, " +
                "b.name, " +
                "a.name AS `Author Name`, " +
                "g.name AS `Genre Name`, " +
                "p.name AS `Publisher Name`, " +
                "b.quantity, " +
                "b.available, " +
                "b.active " +
            "FROM books AS b " +
            "JOIN authors AS a " +
            "ON b.author_id = a.id " +
            "JOIN genres AS g " +
            "ON b.genre_id = g.id " +
            "JOIN publishers AS p " +
            "ON b.publisher_id = p.id ";

    private static String INSERT_BOOK = "INSERT INTO books (`name`, `author_id`, `genre_id`,`publisher_id`, `quantity`, `available`) " +
                                        "VALUES (?,?,?,?,?,?);";

    private static String FIND_BOOK_ID = "" +
            "SELECT " +
                "id, " +
                "COUNT(*) AS count " +
            "FROM books " +
            "WHERE " +
                "name = ? AND " +
                "author_id = ? AND " +
                "genre_id = ? AND " +
                "publisher_id = ?;";

    private static String EXISTS_BY_ID = "SELECT COUNT(*) AS count FROM books WHERE id = ?;";

    private static String GET_BOOKDTO_BY_ID = "{CALL sp_getBookDTO_byId(?)}";

    private static String UPDATE_BOOK_BY_ID =
            "UPDATE books " +
            "SET " +
                "`name` = ?," +
                "`author_id` = ?," +
                "`genre_id` = ?," +
                "`publisher_id` = ? " +
            "WHERE `id` = ?;";

    private static String UPDATE_QUANTITY_BY_ID = "{CALL sp_add_more_quantity(?,?)}";

    private static String DISABLE_BOOK = "UPDATE books SET `active` = '0' WHERE `id` = ?;";

    private static String RE_ACTIVE_BOOK = "UPDATE books SET `active` = '1' WHERE `id` = ?;";

//    private static String SEARCH_BY_FIRST_CHARACTER = "" +
//            "SELECT " +
//                "b.id, " +
//                "b.name, " +
//                "a.name AS `Author Name`, " +
//                "g.name AS `Genre Name`, " +
//                "p.name AS `Publisher Name`, " +
//                "b.quantity, " +
//                "b.available, " +
//                "b.active " +
//            "FROM books AS b " +
//            "JOIN authors AS a " +
//            "ON b.author_id = a.id " +
//            "JOIN genres AS g " +
//            "ON b.genre_id = g.id " +
//            "JOIN publishers AS p " +
//            "ON b.publisher_id = p.id " +
//            "WHERE GetFirstCharacters(??) LIKE CONCAT (?, '%'); ";

//    @Override
//    public List<BookDTO> searchByFirstCharacters(String field_name, String search) {
//        List<BookDTO> bookDTOList = new ArrayList<>();
//        try {
//            Connection connection = MySQLConnUtils.getConnection();
//            PreparedStatement statement = connection.prepareStatement(SEARCH_BY_FIRST_CHARACTER);
//
//            statement.setString(1, field_name);
//            statement.setString(2, search);
//            ResultSet rs = statement.executeQuery();
//            System.out.println(statement);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String author = rs.getString("Author Name");
//                String genre = rs.getString("Genre Name");
//                String publisher = rs.getString("Publisher Name");
//                //int quantity = rs.getInt("quantity");
//                int available = rs.getInt("available");
//                boolean active = rs.getBoolean("active");
//                bookDTOList.add(new BookDTO(id, name, author, genre, publisher, available, active));
//            }
//        } catch (SQLException e) {
//            MySQLConnUtils.printSQLException(e);
//        }
//        return bookDTOList;
//    }

    @Override
    public boolean existsById (int id) {
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(EXISTS_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count == 1)
                    return true;
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return false;
    }

    @Override
    public int findBookId (String name, int authorId, int genreId, int publisherId) {
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOK_ID);
            //preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, authorId);
            preparedStatement.setInt(3, genreId);
            preparedStatement.setInt(4, publisherId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0)
                    return rs.getInt("id");
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return -1;
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS_DTO);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("Author Name");
                String genre = rs.getString("Genre Name");
                String publisher = rs.getString("Publisher Name");
                int available = rs.getInt("available");
                bookDTOList.add(new BookDTO(id, name, author, genre, publisher, available));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return bookDTOList;
    }

    @Override
    public List<BookDTO> searchAll(String query) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            String fullQuery = SEARCH_BOOKS_DTO + query;
            PreparedStatement statement = connection.prepareStatement(fullQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("Author Name");
                String genre = rs.getString("Genre Name");
                String publisher = rs.getString("Publisher Name");
                int available = rs.getInt("available");
                boolean active = rs.getBoolean("active");
                bookDTOList.add(new BookDTO(id, name, author, genre, publisher, available, active));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return bookDTOList;
    }

    @Override
    public BookDTO getBookInfo(int bookId) {
        try {
            Connection connection = MySQLConnUtils.getConnection();
            CallableStatement statement = connection.prepareCall(GET_BOOKDTO_BY_ID);
            statement.setInt(1,bookId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("Author Name");
                String genre = rs.getString("Genre Name");
                String publisher = rs.getString("Publisher Name");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");
                boolean active = rs.getBoolean("active");
                return new BookDTO(id, name, author, genre, publisher, quantity, available,active);
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public boolean create(BookDTO bookDTO) {
        return false;
    }

    @Override
    public boolean update(BookDTO bookDTO) {
        return false;
    }

    @Override
    public boolean create(Book book) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            //preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getGenreId());
            preparedStatement.setInt(4, book.getPublisherId());
            preparedStatement.setInt(5, book.getQuantity());
            preparedStatement.setInt(6, book.getAvailable());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(Book book) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_ID);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getGenreId());
            preparedStatement.setInt(4, book.getPublisherId());
            preparedStatement.setInt(5, book.getId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean addMoreQuantity(int bookId, int amount) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY_BY_ID);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, amount);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean disable(int id) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DISABLE_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean reActive(int id) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RE_ACTIVE_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }
}
