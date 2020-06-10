package com.book.utils;

import com.book.model.presentation.Book;
import com.book.model.presentation.ShoppingBasket;

import java.util.List;

public interface DataConverter {

    Object[][] bookToTableData(List<Book> books);

    String[] bookToTableColumnNames();

    Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket);

    String[] shoppingBasketToTableColumnNames();
}
