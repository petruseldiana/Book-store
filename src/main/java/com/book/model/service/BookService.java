package com.book.model.service;

import com.book.model.presentation.Book;

import java.util.List;
import java.util.Observer;

public interface BookService {

    boolean create(Book book);

    boolean delete(String title, String author);

    boolean update(Book book);

    List<Book> findAll();

    Book findById(Long id);

    Book findByTitle(String title);

    boolean sellBook(String title, int quantity);

    List<Book> search(String title, String author, String genre);

    List<Book> getOutOfStockBooks();

    void addObserver(Observer o);
}
