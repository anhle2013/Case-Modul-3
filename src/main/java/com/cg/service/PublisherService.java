package com.cg.service;

import com.cg.model.Publisher;
import com.cg.utils.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherService implements IPublisherService{

    private static String SELECT_ALL_PUBLISHERS = "" +
            "SELECT " +
                "`id`, " +
                "`name` " +
            "FROM publishers;";

    private static String EXISTS_ID = "" +
            "SELECT COUNT(*) AS count " +
            "FROM publishers " +
            "WHERE id = ?;";

    private static String EXISTS_NAME = "" +
            "SELECT COUNT(*) AS count " +
            "FROM publishers " +
            "WHERE name = ?;";

    private static String INSERT_PUBLISHER = "" +
            "INSERT INTO publishers (`name`) " +
            "VALUES (?);";

    private static String UPDATE_PUBLISHER = "" +
            "UPDATE publishers " +
            "SET name = ? " +
            "WHERE id = ? ;";

    private static String SEARCH_BY_FIRST_CHARACTER = "" +
            "SELECT " +
                "id, " +
                "name " +
            "FROM publishers " +
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
    public List<Publisher> findAll() {
        List<Publisher> publisherList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PUBLISHERS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publisherList.add(new Publisher(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return publisherList;
    }

    @Override
    public List<Publisher> searchAll(String search) {
        List<Publisher> publisherList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SEARCH_BY_FIRST_CHARACTER);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publisherList.add(new Publisher(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return publisherList;
    }

    @Override
    public boolean create(Publisher publisher) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(Publisher publisher) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUBLISHER);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setInt(2, publisher.getId());
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
