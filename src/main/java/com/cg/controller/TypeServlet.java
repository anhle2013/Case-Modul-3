package com.cg.controller;

import com.cg.model.Authors;
import com.cg.model.Genres;
import com.cg.model.Publishers;
import com.cg.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TypeServlet", urlPatterns = "/types")
public class TypeServlet extends HttpServlet {
    IAuthorService authorService;
    IGenreService genreService;
    IPublisherService publisherService;

    @Override
    public void init() throws ServletException {
        authorService = new AuthorService();
        genreService = new GenreService();
        publisherService = new PublisherService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/types/types.jsp");
        if (req.getParameter("type") != null) {
            List<String> errors = new ArrayList<>();
            String type = req.getParameter("type");
            List<Authors> authorList = authorService.findAll();
            List<Genres> genreList = genreService.findAll();
            List<Publishers> publisherList = publisherService.findAll();

            switch (type) {
                case "authors":
                    req.setAttribute("list", authorList);
                    break;
                case "genres":
                    req.setAttribute("list", genreList);
                    break;
                case "publishers":
                    req.setAttribute("list", publisherList);
                    break;
                default:
                    errors.add("NOT FOUND type = " + type);
                    req.setAttribute("errors", errors);

            }
            req.setAttribute("type", type);
        }
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/types/types.jsp");
        String type = req.getParameter("type");
//        String search = req.getParameter("search");
//        List<Authors> searchAuthors = new ArrayList<>();
//        List<Genres> searchGenres = new ArrayList<>();
//        List<Publishers> searchPublishers = new ArrayList<>();
//        switch (type) {
//            case "authors":
//                searchAuthors = authorService.searchAll(search);
//                req.setAttribute("searchList", searchAuthors);
//                break;
//            case "genres":
//                searchGenres = genreService.searchAll(req.getParameter("search"));
//                req.setAttribute("searchList", searchGenres);
//                break;
//            case "publishers":
//                searchPublishers = publisherService.searchAll(req.getParameter("search"));
//                req.setAttribute("searchList", searchPublishers);
//                break;
//        }

        String action = req.getParameter("action");
        String name = req.getParameter("name").trim().replaceAll("\\s+", " ");
        List<String> errors = new ArrayList<>();
        switch (type) {
            case "authors":
                boolean existsAuthor = authorService.existsByName(name);
                if (existsAuthor)
                    errors.add("This Author already exists");
                else {
                    if (action.equals("add")) {
                        Authors author = new Authors(name);
                        boolean success = authorService.create(author);
                        if (success)
                            req.setAttribute("success", "Add new Author Success!");
                    } else {
                        int id = Integer.parseInt(req.getParameter("id"));
                        Authors author = new Authors(id, name);
                        boolean success = authorService.update(author);
                        if (success)
                            req.setAttribute("success", "Update Author Success!");
                    }
                }
                List<Authors> authorList = authorService.findAll();
                req.setAttribute("list", authorList);
                break;
            case "genres":
                boolean existsGenre = genreService.existsByName(name);
                if (existsGenre)
                    errors.add("This genre already exists");
                else {
                    if (action.equals("add")) {
                        Genres genre = new Genres(name);
                        boolean success = genreService.create(genre);
                        if (success)
                            req.setAttribute("success", "Add new Genre Success!");
                    } else {
                        int id = Integer.parseInt(req.getParameter("id"));
                        Genres genre = new Genres(id, name);
                        boolean success = genreService.update(genre);
                        if (success)
                            req.setAttribute("success", "Update Genre Success!");
                    }
                }
                List<Genres> genreList = genreService.findAll();
                req.setAttribute("list", genreList);
                break;
            case "publishers":
                boolean existsPublisher = publisherService.existsByName(name);
                if (existsPublisher)
                    errors.add("This Publisher already exists");
                else {
                    if (action.equals("add")) {
                        Publishers publisher = new Publishers(name);
                        boolean success = publisherService.create(publisher);
                        if (success)
                            req.setAttribute("success", "Add new Publisher Success!");
                    } else {
                        int id = Integer.parseInt(req.getParameter("id"));
                        Publishers publisher = new Publishers(id, name);
                        boolean success = publisherService.update(publisher);
                        if (success)
                            req.setAttribute("success", "Update Publisher Success!");
                    }
                }
                List<Publishers> publisherList = publisherService.findAll();
                req.setAttribute("list", publisherList);
                break;
        }
        if (errors.size() > 0)
            req.setAttribute("errors", errors);

        dispatcher.forward(req, resp);
    }
}
