package com.book.model.service;

import com.book.model.presentation.Book;

import java.util.List;

public interface Report {

    void generateReport(List<Book> books);
}
