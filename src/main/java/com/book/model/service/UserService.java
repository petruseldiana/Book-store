package com.book.model.service;

import com.book.model.presentation.User;

import java.util.List;

public interface UserService {

    User login(String userName, String password);

    boolean create(User user);

    boolean delete(String username);

    boolean update(User user);

    List<User> findAll();

    User findById(Long id);

    User loadByUserName(String username);
}
