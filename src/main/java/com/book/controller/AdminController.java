package com.book.controller;

import com.book.model.presentation.Book;
import com.book.model.presentation.User;
import com.book.model.service.BookService;
import com.book.model.service.ContextHolder;
import com.book.model.service.UserService;
import com.book.model.service.Report;
import com.book.model.service.impl.ReportFactory;
import com.book.view.AdminView;
import com.book.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class AdminController implements Observer {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

    private final AdminView adminView;
    private final LoginView loginView;

    private final BookService bookService;
    private final UserService userService;

    private final ContextHolder contextHolder;

    private static ReportFactory reportFactory;
    private static Report report;

    public AdminController(AdminView adminView, LoginView loginView, BookService bookService, UserService userService, ContextHolder contextHolder) {

        this.adminView = adminView;
        this.loginView = loginView;

        this.bookService = bookService;
        this.userService = userService;
        this.contextHolder = contextHolder;

        this.adminView.addLogoutActionListener(new AddLogoutActionListener());

        this.adminView.addAddUserButtonActionListener(new AddUserActionListener());
        this.adminView.addDelUserButtonActionListener(new DeleteUserActionListener());
        this.adminView.addUpdateUserButtonActionListener(new UpdateUserActionListener());
        this.adminView.addListUserButtonActionListener(new ListUserActionListener());

        this.adminView.addAddBookButtonActionListener(new AddBookActionListener());
        this.adminView.addDelBookButtonActionListener(new DelBookActionListener());
        this.adminView.addUpdateBookButtonActionListener(new UpdateBookActionListener());
        this.adminView.addListBookButtonActionListener(new ListBookActionListener());

        this.adminView.addReportButtonActionListener(new GetReportActionListener());

        this.adminView.setVisible(true);

        bookService.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg.equals("Stock changed!")){
            JOptionPane.showMessageDialog(adminView, "Stock has been updated!");
        }else if(arg.equals("Book null")){
            JOptionPane.showMessageDialog(adminView,"Book not found...try again");
        }
    }

    private class AddLogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            adminView.setVisible(false);

            LOGGER.info("The user " + contextHolder.getCurrentUser().getFirstName() + " " + contextHolder.getCurrentUser().getLastName() + " has logged out!");

            loginView.setVisible(true);
        }
    }

    private class AddUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String firstName = adminView.getFirstName();
            String lastName = adminView.getLastName();
            String address = adminView.getAddress();
            String email = adminView.getEmail();
            String phone = adminView.getPhone();
            double salary = Double.valueOf(adminView.getSalary());
            String username = adminView.getUsername();
            String password = adminView.getPassword();
            String role = adminView.getRole();

            User user = new User(firstName,lastName,address,email,phone,salary,username,password,role);

            if(userService.create(user) == true){
                JOptionPane.showMessageDialog(adminView,"User successfully created!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }
        }
    }

    private class DeleteUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = adminView.getUsername();

            if(userService.delete(username) == true){
                JOptionPane.showMessageDialog(adminView,"User successfully deleted!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }
        }
    }

    private class UpdateUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String firstName = adminView.getFirstName();
            String lastName = adminView.getLastName();
            String address = adminView.getAddress();
            String email = adminView.getEmail();
            String phone = adminView.getPhone();
            double salary = Double.valueOf(adminView.getSalary());
            String username = adminView.getUsername();

            User user = new User(firstName,lastName,address,email,phone,salary,username);

            if(userService.update(user) == true){
                JOptionPane.showMessageDialog(adminView,"User successfully updated!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }

        }
    }

    private class ListUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = adminView.getUsername();

            User user = userService.loadByUserName(username);

            adminView.setFirstName(user.getFirstName());
            adminView.setLastName(user.getLastName());
            adminView.setAddress(user.getAddress());
            adminView.setEmail(user.getEmail());
            adminView.setPhone(user.getPhone());
            adminView.setSalary(String.valueOf(user.getSalary()));
            adminView.setPassword(user.getPassword());
            adminView.setRole(user.getRole());

        }
    }

    private class AddBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String title = adminView.getTitle();
            String author = adminView.getAuthor();
            String genre = adminView.getGenre();
            int quantity = Integer.valueOf(adminView.getQuantity());
            double price = Double.valueOf(adminView.getPrice());

            Book book = new Book(title,author,genre,quantity,price);

            if(bookService.create(book) == true){
                JOptionPane.showMessageDialog(adminView,"Book successfully created!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }
        }
    }

    private class DelBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String title = adminView.getTitle();
            String author = adminView.getAuthor();

            if(bookService.delete(title,author) == true){
                JOptionPane.showMessageDialog(adminView,"Book successfully deleted!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }

        }
    }

    private class UpdateBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String title = adminView.getTitle();
            String author = adminView.getAuthor();
            String genre = adminView.getGenre();
            int quantity = Integer.valueOf(adminView.getQuantity());
            double price = Double.valueOf(adminView.getPrice());

            Book book = new Book(title,author,genre,quantity,price);

            if(bookService.update(book) == true){
                JOptionPane.showMessageDialog(adminView,"Book successfully updated!");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }
        }
    }

    private class ListBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String title = adminView.getTitle();

            Book book = bookService.findByTitle(title);

            adminView.setAuthor(book.getAuthor());
            adminView.setGenreTextField(book.getGenre());
            adminView.setQuantity(String.valueOf(book.getQuantity()));
            adminView.setPrice(String.valueOf(book.getPrice()));
        }
    }

    private class GetReportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Book> books = bookService.getOutOfStockBooks();

            String reportType = "";
            if(adminView.getPDF() == true){
                reportType = "PDF";
            }else if(adminView.getTXT() == true){
                reportType = "TXT";
            }

            reportFactory = new ReportFactory();

            report = reportFactory.getReport(reportType);
            if(books != null){

                report.generateReport(books);
                JOptionPane.showMessageDialog(adminView,"Report generated successfully");
            }else{
                JOptionPane.showMessageDialog(adminView,"Try again");
            }
        }
    }
}
