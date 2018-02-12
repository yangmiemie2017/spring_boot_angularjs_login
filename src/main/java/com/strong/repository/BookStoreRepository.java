package com.strong.repository;

import com.strong.model.entity.BookStore;
import com.strong.model.entity.BookStoreWithBooks;

import java.util.List;

/**
 * @author cy
 */
public interface BookStoreRepository {

    BookStore selectBookStoreById(Long id);

    List<BookStore> selectAllBookStores();

    BookStoreWithBooks selectBookStoreWithBooksById(Long id);

}
