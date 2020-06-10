package com.book.utils.impl;

import com.book.model.presentation.Book;
import com.book.model.presentation.ShoppingBasket;
import com.book.utils.DataConverter;

import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public Object[][] bookToTableData(List<Book> books) {

        Object[][] data = new Object[books.size()][6];

        for (int row = 0; row < data.length; row++) {
            data[row][0] = books.get(row).getId();
            data[row][1] = books.get(row).getTitle();
            data[row][2] = books.get(row).getAuthor();
            data[row][3] = books.get(row).getGenre();
            data[row][4] = books.get(row).getQuantity();
            data[row][5] = books.get(row).getPrice();
        }
        return data;
    }

    @Override
    public String[] bookToTableColumnNames() {
        return new String[]{"Id", "Title", "Author", "Genre", "Quantity", "Price"};
    }

    public Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket) {

        Object[][] data = new Object[shoppingBasket.getShoppingBasketBooks().size()][6];

        for (int row = 0; row < data.length; row++) {
            data[row][0] = shoppingBasket.getShoppingBasketBooks().get(row).getId();
            data[row][1] = shoppingBasket.getShoppingBasketBooks().get(row).getBook().getTitle();
            data[row][2] = shoppingBasket.getShoppingBasketBooks().get(row).getBook().getAuthor();
            data[row][3] = shoppingBasket.getShoppingBasketBooks().get(row).getBook().getGenre();
            data[row][4] = shoppingBasket.getShoppingBasketBooks().get(row).getQuantity();
            data[row][5] = shoppingBasket.getShoppingBasketBooks().get(row).getBook().getPrice();
        }
        return data;
    }

    public String[] shoppingBasketToTableColumnNames() {
        return new String[]{"Id", "Title", "Author", "Genre", "Quantity","Price"};
    }
}
