package com.book.model.repository.impl;

import com.book.configuration.JDBConnectionWrapper;
import com.book.model.presentation.ShoppingBasket;
import com.book.model.presentation.ShoppingBasketBook;
import com.book.model.presentation.User;
import com.book.model.repository.ShoppingBasketBookRepository;
import com.book.model.repository.ShoppingBasketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketRepositoryImpl implements ShoppingBasketRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;
    private final ShoppingBasketBookRepository shoppingBasketBookRepository;

    public ShoppingBasketRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper,
                                        ShoppingBasketBookRepository shoppingBasketBookRepository) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
        this.shoppingBasketBookRepository = shoppingBasketBookRepository;
    }

    @Override
    public List<ShoppingBasket> findAll() {

        Connection connection = jdbConnectionWrapper.getConnection();

        List<ShoppingBasket> shoppingBaskets = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket AS s " +
                            "INNER JOIN user AS u ON s.user_id = u.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ShoppingBasket shoppingBasket = new ShoppingBasket();

                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                shoppingBasket.setId(resultSet.getLong(1));
                shoppingBasket.setUser(user);

                List<ShoppingBasketBook> allByShoppingBasketId = shoppingBasketBookRepository.findAllByShoppingBasketId(shoppingBasket.getId());
                shoppingBasket.setShoppingBasketBooks(allByShoppingBasketId);

                shoppingBaskets.add(shoppingBasket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingBaskets;
    }

    @Override
    public ShoppingBasket findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket AS s " +
                            "INNER JOIN user AS u ON s.user_id = u.id " +
                            "WHERE s.id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                ShoppingBasket shoppingBasket = new ShoppingBasket();
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                shoppingBasket.setId(resultSet.getLong(1));
                shoppingBasket.setUser(user);

                List<ShoppingBasketBook> allByShoppingBasketId = shoppingBasketBookRepository.findAllByShoppingBasketId(id);
                shoppingBasket.setShoppingBasketBooks(allByShoppingBasketId);

                return shoppingBasket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ShoppingBasket save(ShoppingBasket shoppingBasket) {

        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_basket (user_id) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasket.getUser().getId());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                shoppingBasket.setId(resultSet.getLong(1));
                return shoppingBasket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasket update(ShoppingBasket shoppingBasket) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopping_basket SET user_id=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasket.getUser().getId());
            preparedStatement.setLong(2, shoppingBasket.getId());

            int changedRows = preparedStatement.executeUpdate();

            if(changedRows > 0) {
                return shoppingBasket;
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_basket WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
