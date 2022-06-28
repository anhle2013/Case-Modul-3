package com.cg.service;

import com.cg.model.Genre;
import com.cg.utils.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService{

    private static String SELECT_ALL_GENRES = "" +
            "SELECT " +
                "`id`, " +
                "`name` " +
            "FROM genres;";

    private static String EXISTS_ID = "" +
        "SELECT COUNT(*) AS count " +
        "FROM genres " +
        "WHERE id = ?;";

    private static String EXISTS_NAME = "" +
            "SELECT COUNT(*) AS count " +
            "FROM genres " +
            "WHERE name = ?;";

    private static String INSERT_GENRE = "" +
            "INSERT INTO genres (`name`) " +
            "VALUES (?);";

    private static String UPDATE_GENRE = "" +
            "UPDATE genres " +
            "SET name = ? " +
            "WHERE id = ? ;";

    private static String SEARCH_BY_FIRST_CHARACTER = "" +
            "SELECT " +
                "id, " +
                "name " +
            "FROM genres " +
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
    public boolean existsByName(String name) {
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
    public List<Genre> findAll() {
        List<Genre> genreList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GENRES);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                genreList.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return genreList;
    }

    @Override
    public List<Genre> searchAll(String search) {
        List<Genre> genreList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SEARCH_BY_FIRST_CHARACTER);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                genreList.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return genreList;
    }

    @Override
    public boolean create(Genre genre) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENRE);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(Genre genre) {
        boolean success = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GENRE);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setInt(2, genre.getId());
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
