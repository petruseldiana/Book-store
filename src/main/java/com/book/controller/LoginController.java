package com.book.controller;

import com.book.Main;
import com.book.model.presentation.ShoppingBasket;
import com.book.model.presentation.User;
import com.book.model.service.ContextHolder;
import com.book.model.service.ShoppingBasketService;
import com.book.model.service.UserService;
import com.book.view.EmployeeView;
import com.book.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    private final LoginView loginView;
    private final UserService userService;
    private final ContextHolder contextHolder;
    private final ShoppingBasketService shoppingBasketService;
    private final EmployeeView employeeView;

    public LoginController(LoginView loginView, UserService userService, ContextHolder contextHolder, ShoppingBasketService shoppingBasketService, EmployeeView employeeView) {

        this.loginView=loginView;
        this.userService=userService;
        this.contextHolder=contextHolder;
        this.shoppingBasketService = shoppingBasketService;
        this.employeeView = employeeView;

        //add action listener
        this.loginView.addLoginActionListener(new LoginActionListener());

        this.loginView.setVisible(true);
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = loginView.getUserName();
            String password = loginView.getPassword();

            User currentUser = userService.login(username,password);

            if(currentUser != null){

                contextHolder.setCurrentUser(currentUser);
                loginView.getOuter().setVisible(false);

                if(currentUser.getRole().equals("admin")){

                    //open admin
                    Main.openAdminView();
                    LOGGER.info("Successfully admin login!");
                } else{

                    //open user
                    LOGGER.info("Successfully employee login!");

                    ShoppingBasket shoppingBasket = new ShoppingBasket();
                    shoppingBasket.setUser(currentUser);

                    if(shoppingBasketService.create(shoppingBasket) == true){
                        LOGGER.info("Shopping basket created");
                    }
                    else{
                        LOGGER.warning("There already exists a shopping basket");
                    }

                    Main.openEmployeeView();
                }
            }
            else{
                loginView.cleatInputs();
            }
        }
    }
}