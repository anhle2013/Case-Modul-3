package com.cg.service;

import com.cg.dto.BookDTO;
import com.cg.model.Books;

import java.awt.print.Book;

public interface IBookDTOService extends IGeneralService<BookDTO>{
    int findBookId(String name, int authorId, int genreId, int publisherId);

    BookDTO getBookInfo(int bookId);

    boolean create(Books book);

    boolean update(Books book);

    boolean addMoreQuantity(int bookId, int amount);
}
