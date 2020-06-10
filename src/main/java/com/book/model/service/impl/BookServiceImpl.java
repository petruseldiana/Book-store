package com.book.model.service.impl;

import com.book.model.presentation.Book;
import com.book.model.repository.BookRepository;
import com.book.model.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookServiceImpl extends Observable implements BookService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean create(Book book) {

        List<Book> books =  bookRepository.findAll();

        for(Book bk: books){

            if(book.getTitle().equals(bk.getTitle()) && book.getAuthor().equals(bk.getAuthor()) && book.getGenre().equals(bk.getGenre()) && book.getPrice() == bk.getPrice()){

                LOGGER.info("The book " + book.getTitle() + ", " + book.getAuthor() + " already exists, so we'll just update the stock!");
                bookRepository.update(book);
                setChanged();
                notifyObservers("Stock changed!");
                return true;
            }
        }

        if(bookRepository.create(book) == true){

            LOGGER.info("The book was successfully created!");
            return true;
        }

        LOGGER.warning("Something went wrong!");
        return false;
    }

    @Override
    public boolean delete(String title, String author) {

        if(bookRepository.delete(title, author) == true){

            LOGGER.info("The book was successfully deleted!");
            setChanged();
            notifyObservers("Stock changed!");
            return true;
        }

        LOGGER.warning("The book " + title + ", " + author + " was not found!");
        return false;
    }

    @Override
    public boolean update(Book book) {

        if(bookRepository.update(book) == true){
            LOGGER.info("The book was successfully updated!");
            setChanged();
            notifyObservers("Stock changed!");
            return true;
        }

        LOGGER.warning("Something went wrong!");
        return false;
    }

    @Override
    public List<Book> findAll() {

        List books = bookRepository.findAll();
        if(books == null){
            LOGGER.warning("There are not books in the store!");
            return null;
        }

        LOGGER.info("Books found");
        return books;
    }

    @Override
    public Book findById(Long id) {

        Book book = bookRepository.findById(id);

        if(book == null){
            LOGGER.warning("The book does not exist!");
            setChanged();
            notifyObservers("Book null!");
            return null;
        }
        LOGGER.info("The book " + book.getTitle() + ", " + book.getAuthor() + " was found!");
        return book;
    }

    @Override
    public Book findByTitle(String title) {

        Book book = bookRepository.findByTitle(title);

        if(book == null){
            LOGGER.warning("The book " + title + " was not found!");
            return null;
        }

        LOGGER.info("The book " + title + " was found!");
        return book;
    }

    @Override
    public boolean sellBook(String title, int quantity) {

        Book book = bookRepository.findByTitle(title);

        if(quantity != 0){

            if(book.getQuantity() >= quantity){

                int cnt = book.getQuantity() - quantity;
                book.setQuantity(cnt);
                setChanged();
                notifyObservers("Stock changed!");
                bookRepository.update(book);
                return true;
            }else{
                LOGGER.warning("There are not enough books in stock!");
                return false;
            }
        }else{
            LOGGER.warning("Incorrect quantity!");
            return false;
        }
    }

    @Override
    public List<Book> search(String title, String author, String genre) {

        List<Book> books = bookRepository.findAll();

        List<Book> result = books.stream()
                .filter(c -> c.getTitle().equals(title) || c.getAuthor().equals(author) || c.getGenre().equals(genre))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Book> getOutOfStockBooks() {

        List<Book> books = bookRepository.findAll();

        List<Book> result = books.stream()
                .filter(c -> c.getQuantity() == 0)
                .collect(Collectors.toList());

        return result;
    }
}
