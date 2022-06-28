package com.cg.controller;

import com.cg.dto.BookDTO;
import com.cg.model.Authors;
import com.cg.model.Books;
import com.cg.model.Genres;
import com.cg.model.Publishers;
import com.cg.service.*;
import com.cg.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    IBookDTOService bookDTOService;
    IAuthorService authorService;
    IGenreService genreService;
    IPublisherService publisherService;

    @Override
    public void init() throws ServletException {
        bookDTOService = new BookDTOService();
        authorService = new AuthorService();
        genreService = new GenreService();
        publisherService = new PublisherService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "show":
                showListPage(req,resp);
                break;
            case "add":
                showCreatePage(req,resp);
                break;
            case "edit":
                showEditPage(req,resp);
                break;
            case "disable":
            case "reActive":
            case "select":
                showSelectPage(req,resp);
                break;
            default:
                showSearchPage(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "add":
                doCreate(req,resp);
                break;
            case "edit":
                doUpdate(req,resp);
                doAddMoreQuantity(req,resp);
                break;
        }
    }

    public void showListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/books.jsp");
        List<BookDTO> bookDTOList = bookDTOService.findAll();
        List<String> errors = new ArrayList<>();
        int count = bookDTOList.size();
        int maxPageIndex;
        if (count % 10 == 0)
            maxPageIndex = count / 10;
        else
            maxPageIndex = (count / 10) + 1;
        String strPageIndex = req.getParameter("page_index");
        boolean pageIsNumber = ValidateUtils.isNumberValid(strPageIndex);
        if (!pageIsNumber)
            errors.add("Page index INVALID");
        else {
            int pageIndex = Integer.parseInt(strPageIndex);
            if (pageIndex < 1 || pageIndex > maxPageIndex)
                errors.add("Page index must be 1 to " + maxPageIndex);
            else {
                req.setAttribute("bookDTOList", bookDTOList);
                req.setAttribute("maxPageIndex", maxPageIndex);
                req.setAttribute("pageIndex", pageIndex);
            }
        }
        if (errors.size() > 0)
            req.setAttribute("errors", errors);
        dispatcher.forward(req, resp);
    }

    public void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/add.jsp");
        List<Authors> authorList = authorService.findAll();
        List<Genres> genreList = genreService.findAll();
        List<Publishers> publisherList = publisherService.findAll();
        req.setAttribute("authorList", authorList);
        req.setAttribute("genreList", genreList);
        req.setAttribute("publisherList", publisherList);
        dispatcher.forward(req,resp);
    }

    protected void showSearchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/search.jsp");
        String field_name = req.getParameter("field_name");
        String search = "";
        if (req.getParameter("search") != null)
            search = req.getParameter("search").trim().replaceAll("\\s+", " ").toLowerCase();
        String query = "";
        int firstIndex = search.indexOf("'");
        int lastIndex = search.lastIndexOf("'");
        if (firstIndex == 0 && lastIndex == search.length() -1) {
            switch (field_name) {
                case "name":
                    query = "WHERE LOWER(b.name) = " + search + ";";
                    break;
                case "author":
                    query = "WHERE LOWER(a.name) = " + search + ";";
                    break;
                case "genre":
                    query = "WHERE LOWER(g.name) = " + search + ";";
                    break;
                case "publisher":
                    query = "WHERE LOWER(p.name) = " + search + ";";
                    break;
                case "disable":
                    query = "WHERE b.active = '0';";
                    break;
            }
        } else {
            switch (field_name) {
                case "name":
                    query = "WHERE LOWER(b.name) LIKE '%" + search + "%';";
                    break;
                case "author":
                    query = "WHERE LOWER(a.name) LIKE '%" + search + "%';";
                    break;
                case "genre":
                    query = "WHERE LOWER(g.name) LIKE '%" + search + "%';";
                    break;
                case "publisher":
                    query = "WHERE LOWER(p.name) LIKE '%" + search + "%';";
                    break;
                case "disable":
                    query = "WHERE b.active = '0';";
                    break;
            }
        }
        List<BookDTO> bookDTOList = bookDTOService.searchAll(query);
        req.setAttribute("bookDTOList", bookDTOList);
        dispatcher.forward(req,resp);
    }

    protected void showSelectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/select.jsp");
        List<String> errors = new ArrayList<>();
        String strId = req.getParameter("id");
        boolean idIsNumber = ValidateUtils.isNumberValid(strId);
        if (idIsNumber) {
            int id = Integer.parseInt(strId);
            boolean exists = bookDTOService.existsById(id);
            if (!exists)
                errors.add("NOT found any book with id = " + strId);
            else {
                BookDTO bookDTO = bookDTOService.getBookInfo(id);
                req.setAttribute("bookDTO", bookDTO);
                String action = req.getParameter("action");
                if (action.equals("disable")) {
                    boolean check = bookDTO.isActive();
                    if (check) {
                        boolean success = bookDTOService.disable(id);
                        if (success)
                            req.setAttribute("success", "Disable book id = " + id + " Successfull!");
                        bookDTO = bookDTOService.getBookInfo(id);
                        req.setAttribute("bookDTO", bookDTO);
                    } else
                        errors.add("This book already DISABLE");
                }
                if (action.equals("reActive")) {
                    boolean check = bookDTO.isActive();
                    if (!check) {
                        boolean success = bookDTOService.reActive(id);
                        if (success)
                            req.setAttribute("success", "ReActive book id = " + id + " Successfull!");
                        bookDTO = bookDTOService.getBookInfo(id);
                        req.setAttribute("bookDTO", bookDTO);
                    } else
                        errors.add("This book already ACTIVE");
                }
            }
        } else
            errors.add("Book id must be a number");
        if (errors.size() > 0)
            req.setAttribute("errors", errors);
        dispatcher.forward(req,resp);
    }

    public void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/edit.jsp");
        List<String> errorsId = new ArrayList<>();
        String strId = req.getParameter("id");
        boolean idIsNumber = ValidateUtils.isNumberValid(strId);
        if (idIsNumber) {
            int id = Integer.parseInt(strId);
            boolean exists = bookDTOService.existsById(id);
            if (!exists)
                errorsId.add("Book id NOT available");
            else {
                BookDTO bookDTO = bookDTOService.getBookInfo(id);
                List<Authors> authorList = authorService.findAll();
                List<Genres> genreList = genreService.findAll();
                List<Publishers> publisherList = publisherService.findAll();
                req.setAttribute("authorList", authorList);
                req.setAttribute("genreList", genreList);
                req.setAttribute("publisherList", publisherList);
                req.setAttribute("bookDTO", bookDTO);
            }
        } else
            errorsId.add("Book id must be a number");
        if (errorsId.size() > 0)
            req.setAttribute("errorsId", errorsId);
        dispatcher.forward(req,resp);
    }

    public void doCreate (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/add.jsp");
        String name = req.getParameter("name").trim().replaceAll("\\s+", " ");
        String strAuthorId = req.getParameter("authorId").trim();
        String strGenreId = req.getParameter("genreId").trim();
        String strPublisherId = req.getParameter("publisherId").trim();
        String strQuantity = req.getParameter("quantity").trim();

        int authorId = -1, genreId = -1, publisherId = -1;
        boolean existsAuthorId, existsGenreId, existsPublisherId;
        List<String> errors = new ArrayList<>();
        boolean authorIdIsNumber = ValidateUtils.isNumberValid(strAuthorId);
        if (!authorIdIsNumber)
            errors.add("Author id must be number");
        else {
            authorId = Integer.parseInt(strAuthorId);
            existsAuthorId = authorService.existsById(authorId);
            if (!existsAuthorId)
                errors.add("Author id NOT available");
        }
        boolean genreIdIsNumber = ValidateUtils.isNumberValid(strGenreId);
        if (!genreIdIsNumber)
            errors.add("Genre id must be number");
        else {
            genreId = Integer.parseInt(strGenreId);
            existsGenreId = genreService.existsById(genreId);
            if (!existsGenreId)
                errors.add("Genre id NOT available");
        }
        boolean publisherIdIsNumber = ValidateUtils.isNumberValid(strPublisherId);
        if (!publisherIdIsNumber)
            errors.add("Publisher id must be number");
        else {
            publisherId = Integer.parseInt(strPublisherId);
            existsPublisherId = publisherService.existsById(publisherId);
            if (!existsPublisherId)
                errors.add("Publisher id NOT available");
        }

        boolean quantityIsNumber = ValidateUtils.isNumberValid(strQuantity);
        if (!quantityIsNumber)
            errors.add("Quantity must be number");
        if (errors.size() == 0) {
            int quantity = Integer.parseInt(strQuantity);
            if (quantity <= 0 || quantity > 99)
                errors.add("Quantity must be from 1 to 99");
            else {
                int id = bookDTOService.findBookId(name, authorId, genreId, publisherId);
                if (id == -1) {
                    Books book = new Books(name, authorId, genreId, publisherId, quantity, quantity);
                    boolean success = bookDTOService.create(book);
                    req.setAttribute("success", success);
                } else
                    errors.add("This book already exists with id = " + id);
            }
        }
        if (errors.size() > 0)
            req.setAttribute("errors", errors);
        List<Authors> authorList = authorService.findAll();
        List<Genres> genreList = genreService.findAll();
        List<Publishers> publisherList = publisherService.findAll();
        req.setAttribute("authorList", authorList);
        req.setAttribute("genreList", genreList);
        req.setAttribute("publisherList", publisherList);
        dispatcher.forward(req, resp);
    }

    public void doUpdate (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("name")!=null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/edit.jsp");
            int id = Integer.parseInt(req.getParameter("id"));

            String name = req.getParameter("name").trim().replaceAll("\\s+", " ");
            String strAuthorId = req.getParameter("authorId").trim();
            String strGenreId = req.getParameter("genreId").trim();
            String strPublisherId = req.getParameter("publisherId").trim();

            int authorId = -1, genreId = -1, publisherId = -1;
            boolean existsAuthorId, existsGenreId, existsPublisherId;
            List<String> errors = new ArrayList<>();
            boolean authorIdIsNumber = ValidateUtils.isNumberValid(strAuthorId);
            if (!authorIdIsNumber)
                errors.add("Author id must be number");
            else {
                authorId = Integer.parseInt(strAuthorId);
                existsAuthorId = authorService.existsById(authorId);
                if (!existsAuthorId)
                    errors.add("Author id NOT available");
            }
            boolean genreIdIsNumber = ValidateUtils.isNumberValid(strGenreId);
            if (!genreIdIsNumber)
                errors.add("Genre id must be number");
            else {
                genreId = Integer.parseInt(strGenreId);
                existsGenreId = genreService.existsById(genreId);
                if (!existsGenreId)
                    errors.add("Genre id NOT available");
            }
            boolean publisherIdIsNumber = ValidateUtils.isNumberValid(strPublisherId);
            if (!publisherIdIsNumber)
                errors.add("Publisher id must be number");
            else {
                publisherId = Integer.parseInt(strPublisherId);
                existsPublisherId = publisherService.existsById(publisherId);
                if (!existsPublisherId)
                    errors.add("Publisher id NOT available");
            }

            if (errors.size() == 0) {
                int checkId = bookDTOService.findBookId(name, authorId, genreId, publisherId);
                if (checkId == -1) {
                    Books book = new Books(id, name, authorId, genreId, publisherId);
                    boolean success = bookDTOService.update(book);
                    if (success)
                        req.setAttribute("success", "Update Book infomation Successfull!");
                } else
                    errors.add("This book already exists with id = " + checkId);
            }
            if (errors.size() > 0)
                req.setAttribute("errors", errors);
            BookDTO bookDTO = bookDTOService.getBookInfo(id);
            List<Authors> authorList = authorService.findAll();
            List<Genres> genreList = genreService.findAll();
            List<Publishers> publisherList = publisherService.findAll();
            req.setAttribute("bookDTO", bookDTO);
            req.setAttribute("authorList", authorList);
            req.setAttribute("genreList", genreList);
            req.setAttribute("publisherList", publisherList);
            dispatcher.forward(req, resp);
        }
    }

    public void doAddMoreQuantity (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("quantity")!=null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/library/book/edit.jsp");
            int id = Integer.parseInt(req.getParameter("id"));
            String strQuantity = req.getParameter("quantity").trim();
            List<String> errors = new ArrayList<>();
            int quantity = 0;
            boolean quantityIsNumber = ValidateUtils.isNumberValid(strQuantity);
            if (!quantityIsNumber)
                errors.add("Quantity must be number");
            else {
                quantity = Integer.parseInt(strQuantity);
                if (quantity <= 0 || quantity > 99)
                    errors.add("Quantity must be from 1 to 99");
                else {
                    boolean success = bookDTOService.addMoreQuantity(id, quantity);
                    req.setAttribute("success", "Add more Quantity Successfull!");
                }
            }
            if (errors.size() > 0)
                req.setAttribute("errors", errors);
            BookDTO bookDTO = bookDTOService.getBookInfo(id);
            List<Authors> authorList = authorService.findAll();
            List<Genres> genreList = genreService.findAll();
            List<Publishers> publisherList = publisherService.findAll();
            req.setAttribute("bookDTO", bookDTO);
            req.setAttribute("authorList", authorList);
            req.setAttribute("genreList", genreList);
            req.setAttribute("publisherList", publisherList);
            dispatcher.forward(req, resp);
        }
    }
}
