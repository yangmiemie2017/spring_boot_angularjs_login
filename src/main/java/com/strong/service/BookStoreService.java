package com.strong.service;

import com.strong.model.entity.BookStore;
import com.strong.model.entity.BookStoreWithBooks;

import java.util.List;
import java.util.Optional;

/**
 * @author cy
 */
public interface BookStoreService {

    Optional<BookStore> getBookStoreById(Long id);

    List<String> getAllBookStoreNames();

    Optional<BookStoreWithBooks> getBookStoreWithBooksById(Long id);

}
