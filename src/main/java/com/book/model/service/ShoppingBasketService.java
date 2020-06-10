package com.book.model.service;

import com.book.model.presentation.ShoppingBasket;

import java.util.Observer;

public interface ShoppingBasketService {

    boolean checkout(Long shoppingBasketId);

    boolean create(ShoppingBasket shoppingBasket);

    void addArticle(Long shoppingBasketId, Long articleId, int quantity);

    void delete(Long shoppingBasketId);

    ShoppingBasket findById(Long id);

    ShoppingBasket findByUserId(Long userId);

    void addObserver(Observer o);
}
