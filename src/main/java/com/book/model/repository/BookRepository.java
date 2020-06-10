package com.book.model.repository;

import com.book.model.presentation.Book;

import java.util.List;

public interface BookRepository {

    boolean create(Book book);

    boolean delete(String title, String author);

    boolean update(Book book);

    List<Book> findAll();

    Book findById(Long id);

    Book findByTitle(String title);
}
