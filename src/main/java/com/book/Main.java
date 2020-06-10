package com.book;

import com.book.configuration.JDBConnectionWrapper;
import com.book.controller.AdminController;
import com.book.controller.EmployeeController;
import com.book.controller.LoginController;
import com.book.model.repository.BookRepository;
import com.book.model.repository.ShoppingBasketBookRepository;
import com.book.model.repository.ShoppingBasketRepository;
import com.book.model.repository.UserRepository;
import com.book.model.repository.impl.BookRepositoryImpl;
import com.book.model.repository.impl.ShoppingBasketBookRepositoryImpl;
import com.book.model.repository.impl.ShoppingBasketRepositoryImpl;
import com.book.model.repository.impl.UserRepositoryImpl;
import com.book.model.service.BookService;
import com.book.model.service.ContextHolder;
import com.book.model.service.ShoppingBasketService;
import com.book.model.service.UserService;
import com.book.model.service.impl.BookServiceImpl;
import com.book.model.service.impl.ContextHolderImpl;
import com.book.model.service.impl.ShoppingBasketServiceImpl;
import com.book.model.service.impl.UserServiceImpl;
import com.book.utils.DataConverter;
import com.book.utils.impl.DataConverterImpl;
import com.book.view.AdminView;
import com.book.view.EmployeeView;
import com.book.view.LoginView;

public class Main {

    private static final String SCHEMA_NAME = "book_store";

    private static UserRepository userRepository;
    private static BookRepository bookRepository;
    private static ShoppingBasketBookRepository shoppingBasketBookRepository;
    private static ShoppingBasketRepository shoppingBasketRepository;

    private static DataConverter dataConverter;
    private static UserService userService;
    private static BookService bookService;
    private static ShoppingBasketService shoppingBasketService;
    private static ContextHolder contextHolder;

    private static LoginController loginController;
    private static AdminController adminController;
    private static EmployeeController employeeController;

    private static LoginView loginView;
    private static AdminView adminView;
    private static EmployeeView employeeView;

    public static void main(String[] args){

        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper(SCHEMA_NAME);

        userRepository = new UserRepositoryImpl(jdbConnectionWrapper);
        bookRepository = new BookRepositoryImpl(jdbConnectionWrapper);
        shoppingBasketBookRepository = new ShoppingBasketBookRepositoryImpl(jdbConnectionWrapper);
        shoppingBasketRepository = new ShoppingBasketRepositoryImpl(jdbConnectionWrapper, shoppingBasketBookRepository);

        dataConverter = new DataConverterImpl();
        userService = new UserServiceImpl(userRepository);
        bookService = new BookServiceImpl(bookRepository);
        shoppingBasketService = new ShoppingBasketServiceImpl(shoppingBasketRepository,shoppingBasketBookRepository,bookRepository, bookService);
        contextHolder = new ContextHolderImpl();

        loginView = new LoginView();
        adminView = new AdminView();
        employeeView = new EmployeeView();

        //User user = new User("Diana", "Petrusel", "Magnoliei 22","petrusel.diana@yahoo.com","0754616678",4000,"diana","000000","admin");
        //userService.create(user);

        openLogin();
    }

    public static void openLogin(){
        loginController = new LoginController(loginView,userService,contextHolder, shoppingBasketService, employeeView);
    }

    public static void openAdminView(){

        adminController = new AdminController(adminView, loginView, bookService,userService,contextHolder);
    }

    public static void openEmployeeView(){

        employeeController = new EmployeeController(employeeView, loginView, dataConverter, shoppingBasketService, bookService, contextHolder);
    }

}
