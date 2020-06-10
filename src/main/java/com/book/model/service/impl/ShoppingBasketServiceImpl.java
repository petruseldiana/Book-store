package com.book.model.service.impl;

import com.book.model.presentation.Book;
import com.book.model.presentation.ShoppingBasket;
import com.book.model.presentation.ShoppingBasketBook;
import com.book.model.repository.BookRepository;
import com.book.model.repository.ShoppingBasketBookRepository;
import com.book.model.repository.ShoppingBasketRepository;
import com.book.model.service.BookService;
import com.book.model.service.ShoppingBasketService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Logger;

public class ShoppingBasketServiceImpl extends Observable implements ShoppingBasketService {

    private static final Logger LOGGER = Logger.getLogger(ShoppingBasketServiceImpl.class.getName());

    private final ShoppingBasketRepository shoppingBasketRepository;
    private final ShoppingBasketBookRepository shoppingBasketBookRepository;

    private final BookRepository bookRepository;
    private final BookService bookService;

    public ShoppingBasketServiceImpl(ShoppingBasketRepository shoppingBasketRepository, ShoppingBasketBookRepository shoppingBasketBookRepository, BookRepository bookRepository, BookService bookService) {
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.shoppingBasketBookRepository = shoppingBasketBookRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    public boolean checkout(Long shoppingBasketId) {

        //ShoppingBasket shoppingBasket = shoppingBasketRepository.findById(shoppingBasketId);

        List<ShoppingBasketBook>  shoppingBasketBooks = shoppingBasketBookRepository.findAllByShoppingBasketId(shoppingBasketId);
        List<Book> books = bookService.findAll();

        for(ShoppingBasketBook sbb: shoppingBasketBooks){

            Long id = 0L;
            Book book = sbb.getBook();

            for(Book bk: books){
                if(book.getTitle().equals(bk.getTitle()) && book.getAuthor().equals(bk.getAuthor()) && book.getPrice() == bk.getPrice() && book.getGenre().equals(bk.getGenre())){
                    id = bk.getId();
                }
            }
//            System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getPrice() + " "+book.getId());
//            System.out.println(book.getId());

            if(id == 0L){

                LOGGER.warning("Book not found");
                return false;
            }

            Book bookTable = bookService.findById(id);

            int quantity = bookTable.getQuantity() - sbb.getQuantity();
//            System.out.println(quantity);
            bookTable.setQuantity(quantity);

            bookService.update(bookTable);

            shoppingBasketBookRepository.delete(sbb.getId());
        }

        setChanged();
        notifyObservers();
        LOGGER.info("Shopping basket clean");
        return true;
    }

    @Override
    public boolean create(ShoppingBasket shoppingBasket) {

        if(shoppingBasketRepository.findById(shoppingBasket.getUser().getId()) != null){
//            System.out.println("found: " + shoppingBasketRepository.findById( shoppingBasket.getUser().getId()).getId());
            LOGGER.warning("The user " + shoppingBasket.getUser().getUsername() + " already has a shopping basket!");
            return false;
        }

        shoppingBasketRepository.save(shoppingBasket);
        LOGGER.warning("Shopping basket created successfully");
        return true;
    }

    @Override
    public void addArticle(Long shoppingBasketId, Long bookId, int quantity) {

        ShoppingBasket basket = shoppingBasketRepository.findById(shoppingBasketId);

        Optional<ShoppingBasketBook> shoppingBasketItemOptional = basket.getShoppingBasketBooks()
                .stream()
                .filter(shoppingBasketItem -> shoppingBasketItem.getBook().getId().equals(bookId))
                .findFirst();

        if (shoppingBasketItemOptional.isPresent()) {
            //exista deja, adaugam cantitate
            ShoppingBasketBook shoppingBasketItem = shoppingBasketItemOptional.get();
            int existingQuantity = shoppingBasketItem.getQuantity();
            shoppingBasketItem.setQuantity(existingQuantity + quantity);

            shoppingBasketBookRepository.update(shoppingBasketItem);
        } else {
            //cream un sbItem nou
            ShoppingBasketBook shoppingBasketBook = new ShoppingBasketBook();

            Book book = bookRepository.findById(bookId);

            shoppingBasketBook.setBook(book);
            shoppingBasketBook.setQuantity(quantity);
            shoppingBasketBook.setShoppingBasketId(shoppingBasketId);

            shoppingBasketBookRepository.create(shoppingBasketBook);
        }

        setChanged();
        notifyObservers();
    }

    @Override
    public void delete(Long shoppingBasketId) {
        LOGGER.warning("Method not implemented");
    }


    @Override
    public ShoppingBasket findById(Long id) {
        return shoppingBasketRepository.findById(id);
    }

    @Override
    public ShoppingBasket findByUserId(Long userId) {

        return shoppingBasketRepository.findAll()
                .stream()
                .filter(shoppingBasket -> shoppingBasket.getUser().getId().equals(userId))
                .findFirst().get();
    }
}
