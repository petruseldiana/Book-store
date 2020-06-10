package com.book.model.repository;

import com.book.model.presentation.ShoppingBasketBook;

import java.util.List;

public interface ShoppingBasketBookRepository {

    List<ShoppingBasketBook> findAllByShoppingBasketId(Long shoppingBasketId);

    ShoppingBasketBook findByShoppingBasketIdAndByBookId(Long shoppingBasketId, Long bookId);

    ShoppingBasketBook create(ShoppingBasketBook shoppingBasketBook);

    ShoppingBasketBook update(ShoppingBasketBook shoppingBasketBook);

    boolean delete(Long id);
}

