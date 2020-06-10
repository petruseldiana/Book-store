package com.book.model.repository.impl;

import com.book.configuration.JDBConnectionWrapper;
import com.book.model.presentation.Book;
import com.book.model.presentation.ShoppingBasketBook;
import com.book.model.repository.ShoppingBasketBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketBookRepositoryImpl implements ShoppingBasketBookRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;

    public ShoppingBasketBookRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<ShoppingBasketBook> findAllByShoppingBasketId(Long shoppingBasketId) {

        Connection connection = jdbConnectionWrapper.getConnection();

        List<ShoppingBasketBook> shoppingBasketItems = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN book AS b ON s.item_id = b.id " +
                            "WHERE shopping_basket_id=?");

            preparedStatement.setLong(1, shoppingBasketId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ShoppingBasketBook shoppingBasketBook = new ShoppingBasketBook();
                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setPrice(resultSet.getDouble("price"));

                shoppingBasketBook.setId(resultSet.getLong(1));
                shoppingBasketBook.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketBook.setBook(book);
                shoppingBasketBook.setQuantity(resultSet.getInt(4));

                shoppingBasketItems.add(shoppingBasketBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingBasketItems;
    }

    @Override
    public ShoppingBasketBook findByShoppingBasketIdAndByBookId(Long shoppingBasketId, Long bookId) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN book AS a ON s.item_id = a.id " +
                            "WHERE shopping_basket_id=? AND item_id=?");
            preparedStatement.setLong(1, shoppingBasketId);
            preparedStatement.setLong(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ShoppingBasketBook shoppingBasketBook = new ShoppingBasketBook();
                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setPrice(resultSet.getDouble("price"));

                shoppingBasketBook.setId(resultSet.getLong(1));
                shoppingBasketBook.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketBook.setBook(book);
                shoppingBasketBook.setQuantity(resultSet.getInt(4));

                return shoppingBasketBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketBook create(ShoppingBasketBook shoppingBasketBook) {

        Connection connection = jdbConnectionWrapper.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_basket_item (shopping_basket_id, item_id, quantity) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, shoppingBasketBook.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketBook.getBook().getId());
            preparedStatement.setInt(3, shoppingBasketBook.getQuantity());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {

                shoppingBasketBook.setId(resultSet.getLong(1));

                return shoppingBasketBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketBook update(ShoppingBasketBook shoppingBasketItem) {

        Connection connection = jdbConnectionWrapper.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopping_basket_item SET shopping_basket_id=?, item_id=?, quantity=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, shoppingBasketItem.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketItem.getBook().getId());
            preparedStatement.setInt(3, shoppingBasketItem.getQuantity());
            preparedStatement.setLong(4, shoppingBasketItem.getId());

            int changedRows = preparedStatement.executeUpdate();

            if(changedRows > 0) {
                return shoppingBasketItem;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {

        Connection connection = jdbConnectionWrapper.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_basket_item WHERE id= ?");

            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
