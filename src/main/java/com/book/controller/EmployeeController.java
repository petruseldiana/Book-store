package com.book.controller;

import com.book.model.presentation.Book;
import com.book.model.presentation.ShoppingBasket;
import com.book.model.service.BookService;
import com.book.model.service.ContextHolder;
import com.book.model.service.ShoppingBasketService;
import com.book.utils.DataConverter;
import com.book.utils.impl.DataConverterImpl;
import com.book.view.EmployeeView;
import com.book.view.LoginView;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class EmployeeController implements Observer {

    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

    private final EmployeeView employeeView;
    private final LoginView loginView;

    private final DataConverter dataConverter;

    private final ShoppingBasketService shoppingBasketService;
    private final BookService bookService;
    private final ContextHolder contextHolder;

    public EmployeeController(EmployeeView employeeView, LoginView loginView, DataConverter dataConverter, ShoppingBasketService shoppingBasketService, BookService bookService, ContextHolder contextHolder) {

        this.employeeView = employeeView;
        this.loginView = loginView;

        this.dataConverter = dataConverter;

        this.shoppingBasketService = shoppingBasketService;
        this.bookService = bookService;
        this.contextHolder = contextHolder;

        final List<Book> books = this.bookService.findAll();
        final ShoppingBasket shoppingBasket = this.shoppingBasketService.findByUserId(contextHolder.getCurrentUser().getId());

        final Object[][] bookData = this.dataConverter.bookToTableData(books);
        final String[] bookColumnNames = this.dataConverter.bookToTableColumnNames();

        final Object[][] shoppingBasketData = dataConverter.shoppingBasketToTableData(shoppingBasket);
        final String[] shoppingBasketBooksColumnNames = dataConverter.shoppingBasketToTableColumnNames();

        //set data
        this.employeeView.setLoggedInUser(contextHolder.getCurrentUser().getUsername());
        this.employeeView.refreshBookTable(bookData, bookColumnNames);
        this.employeeView.refreshShoppingBasketTable(shoppingBasketData, shoppingBasketBooksColumnNames);
        this.employeeView.setShoppingBasketId(shoppingBasket.getId());

        //add action listeners
        this.employeeView.addAddButtonActionListener(new AddToBasketActionListener());
        this.employeeView.addLogoutActionListener(new LogoutActionListener());
        this.employeeView.addSellButtonActionListener(new SellBooksActionListener());
        this.employeeView.addShowActionListener(new ShowBooksActionListener());
        this.employeeView.addSearchActionListener(new SearchBookActionListener());

        //add observers
        bookService.addObserver(this);
        shoppingBasketService.addObserver(this);

        this.employeeView.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

        refreshSelectedShoppingBasket();
        refreshBooksTable();
    }

    private void refreshSelectedShoppingBasket() {

        Long selectedShoppingBasketId = employeeView.getShoppingBasketId();
        ShoppingBasket shoppingBasket = shoppingBasketService.findById(selectedShoppingBasketId);

        String[] shoppingBasketBooksColumnNames = dataConverter.shoppingBasketToTableColumnNames();
        Object[][] shoppingBasketData = dataConverter.shoppingBasketToTableData(shoppingBasket);

        employeeView.refreshSelectedShoppingBasket(shoppingBasket, shoppingBasketData, shoppingBasketBooksColumnNames);
    }

    private void refreshBooksTable() {

        List<Book> books = bookService.findAll();

        final Object[][] bookData = this.dataConverter.bookToTableData(books);
        final String[] bookColumnNames = this.dataConverter.bookToTableColumnNames();

        employeeView.refreshBookTable(bookData, bookColumnNames);
    }

    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            employeeView.setVisible(false);

            LOGGER.info("The user " + contextHolder.getCurrentUser().getFirstName() + " " + contextHolder.getCurrentUser().getLastName() + " has logged out!" );

            loginView.setVisible(true);
        }
    }


    private class AddToBasketActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int quantity = employeeView.getQuantity();

            if(quantity == 0){

                JOptionPane.showMessageDialog(employeeView, "You must introduce quantity");
            }else{

                int selectedBookQuantity = employeeView.getSelectedBookQuantity();
                if(selectedBookQuantity == 0){

                    JOptionPane.showMessageDialog(employeeView,"Book out of stock");
                }else if(selectedBookQuantity >= quantity){

                    Long selectedBookId = employeeView.getSelectedBookId();
                    Long selectedShoppingBasketId = employeeView.getShoppingBasketId();

                    shoppingBasketService.addArticle(selectedShoppingBasketId, selectedBookId, quantity);

                    JOptionPane.showMessageDialog(employeeView,"Book added to shopping basket");
                }else{
                    JOptionPane.showMessageDialog(employeeView,"There are not enough books in stock");
                }
            }
        }
    }

    private class SellBooksActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Long shoppingBasketId = employeeView.getShoppingBasketId();

            if(shoppingBasketService.checkout(shoppingBasketId) == true){

                JOptionPane.showMessageDialog(employeeView,"Books sold with success!");
            }else{
                JOptionPane.showMessageDialog(employeeView,"Try again");
            }
        }
    }

    private class ShowBooksActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshBooksTable();
        }
    }

    private class SearchBookActionListener implements ActionListener {

        private DataConverter dataConverter = new DataConverterImpl();

        @Override
        public void actionPerformed(ActionEvent e) {

            String title = employeeView.getTitleTextField();
            String author = employeeView.getAuthorTextField();
            String genre = employeeView.getGenreTextField();

            List<Book> books = bookService.search(title,author,genre);

            final Object[][] bookData = this.dataConverter.bookToTableData(books);
            final String[] bookColumnNames = this.dataConverter.bookToTableColumnNames();

            employeeView.refreshBookTable(bookData, bookColumnNames);
        }
    }

}
