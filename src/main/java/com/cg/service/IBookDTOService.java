package com.cg.service;

import com.cg.dto.BookDTO;
import com.cg.model.Book;

public interface IBookDTOService extends IGeneralService<BookDTO>{
    int findBookId(String name, int authorId, int genreId, int publisherId);

    BookDTO getBookInfo(int bookId);

    boolean create(Book book);

    boolean update(Book book);

    boolean addMoreQuantity(int bookId, int amount);

//    List<BookDTO> searchByFirstCharacters(String field_name, String search);
}
