package com.cg.service;

import com.cg.model.Author;
import com.cg.utils.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthorService{

    private static String SELECT_ALL_AUTHORS = "" +
            "SELECT " +
                "`id`, " +
                "`name` " +
            "FROM authors;";

    private static String EXISTS_ID = "" +
        "SELECT COUNT(*) AS count " +
        "FROM authors " +
        "WHERE id = ?;";

    private static String EXISTS_NAME = "" +
            "SELECT COUNT(*) AS count " +
            "FROM authors " +
            "WHERE name = ?;";

    private static String INSERT_AUTHOR = "" +
            "INSERT INTO authors (`name`) " +
            "VALUES (?);";

    private static String UPDATE_AUTHOR = "" +
            "UPDATE authors " +
            "SET name = ? " +
            "WHERE id = ? ;";

    private static String SEARCH_BY_FIRST_CHARACTER = "" +
            "SELECT " +
                "id, " +
                "name " +
            "FROM authors " +
            "WHERE GetFirstCharacters(name) LIKE CONCAT (?, '%'); ";
    @Override
    public boolean existsById (int id) {
        boolean exist = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXISTS_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return exist;
    }

    @Override
    public boolean existsByName (String name) {
        boolean exist = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(EXISTS_NAME);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return exist;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AUTHORS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                authorList.add(new Author(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return authorList;
    }

    @Override
    public List<Author> searchAll(String search) {
        List<Author> authorList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SEARCH_BY_FIRST_CHARACTER);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                authorList.add(new Author(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return authorList;
    }

    @Override
    public boolean create(Author author) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR);
            preparedStatement.setString(1, author.getName());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(Author author) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setInt(2, author.getId());
            System.out.println(preparedStatement);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean disable(int id) {
        return false;
    }

    @Override
    public boolean reActive(int id) {
        return false;
    }
}
