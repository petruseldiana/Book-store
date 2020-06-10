package com.book.model.service;

import com.book.model.presentation.User;

public interface ContextHolder {

    void setCurrentUser(User user);

    User getCurrentUser();

}
