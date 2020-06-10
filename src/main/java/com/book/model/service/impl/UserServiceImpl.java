package com.book.model.service.impl;

import com.book.model.presentation.User;
import com.book.model.repository.UserRepository;
import com.book.model.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String userName, String password) {

        User user = userRepository.loadByUserName(userName);

        if(user != null) {
            if(Objects.equals(user.getPassword(), password)) {

                return user;
            } else {
                LOGGER.warning("Wrong password for user " + userName);
            }
        } else {
            LOGGER.warning("User with username: " + userName + " was not found!");
        }
        return null;
    }

    @Override
    public boolean create(User user) {

        List<User> users = new ArrayList<User>();
        users = userRepository.findAll();

        for(User us: users){

            if(user.getUsername().equals(us.getUsername())){
                LOGGER.warning("Username " + user.getUsername() + " already exists...try again!");
                return false;
            }
        }

        if(user.getPassword().length() < 6){
            LOGGER.warning("Password too short!");
            return false;
        }

       if(userRepository.create(user) == true){
           LOGGER.info("The user was successfully created!");
           return true;
       }

        return false;
    }

    @Override
    public boolean delete(String username) {

        if(userRepository.delete(username) == true){
            LOGGER.info("The user " + username + " was deleted successfully!");
            return true;
        }

        LOGGER.warning("The user " + username + " was not found");
        return false;
    }

    @Override
    public boolean update(User user) {

        if(userRepository.update(user) == true){

            LOGGER.info("Information about " + user.getUsername() + " was successfully updated!");
            return true;
        }

        LOGGER.warning("The user " + user.getUsername() + " was not found");
        return false;
    }

    @Override
    public List<User> findAll() {

        List users = userRepository.findAll();

        if(users == null){

            LOGGER.info("There are no users!");
            return null;
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id);

        if(user == null){

            LOGGER.warning("The user does not exists!");
            return null;
        }

        return user;
    }

    @Override
    public User loadByUserName(String username) {

        User user = userRepository.loadByUserName(username);

        if(user == null){
            LOGGER.warning("The user " + user.getUsername() + " was not found!");
            return null;
        }

        LOGGER.info("The user " + user.getUsername() + " was found!");
        return user;

    }
}
