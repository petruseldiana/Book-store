package com.book.model.service.impl;

import com.book.model.presentation.User;
import com.book.model.service.ContextHolder;

public class ContextHolderImpl implements ContextHolder {
    private User currentUser;

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
