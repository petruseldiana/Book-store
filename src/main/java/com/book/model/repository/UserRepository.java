package com.book.model.repository;

import com.book.model.presentation.User;

import java.util.List;

public interface UserRepository {

    boolean create(User user);

    boolean delete(String username);

    boolean update(User user);

    List<User> findAll();

    User findById(Long id);

    User loadByUserName(String username);
}
