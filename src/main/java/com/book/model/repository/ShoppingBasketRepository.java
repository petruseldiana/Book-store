package com.book.model.repository;

import com.book.model.presentation.ShoppingBasket;

import java.util.List;

public interface ShoppingBasketRepository {

    List<ShoppingBasket> findAll();

    ShoppingBasket findById(Long id);

    ShoppingBasket save(ShoppingBasket shoppingBasket);

    ShoppingBasket update(ShoppingBasket shoppingBasket);

    boolean delete(Long id);
}
