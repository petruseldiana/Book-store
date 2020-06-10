package com.book.model.repository.impl;

import com.book.configuration.JDBConnectionWrapper;
import com.book.model.presentation.Book;
import com.book.model.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;

    public BookRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public boolean create(Book book) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book(title, author, genre, quantity, price) " +
                            "VALUES (?,?,?,?,?);"
            );

            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getGenre());
            preparedStatement.setInt(4,book.getQuantity());
            preparedStatement.setDouble(5,book.getPrice());

            preparedStatement.execute();

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String title, String author) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM book WHERE title = ? and author = ?;"
            );

            preparedStatement.setString(1,title);
            preparedStatement.setString(2,author);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean update(Book book) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE book SET " +
                            "title = ?, " +
                            "author = ?, " +
                            "genre = ?, " +
                            "quantity = ?, " +
                            "price = ? " +
                            "WHERE title = ?"
            );

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getGenre());
            preparedStatement.setInt(4,book.getQuantity());
            preparedStatement.setDouble(5,book.getPrice());
            preparedStatement.setString(6, book.getTitle());

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Book> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();

        List<Book> books = new ArrayList<Book>();

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT *FROM book;"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setPrice(resultSet.getDouble("price"));

                books.add(book);
            }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return books;
    }

    @Override
    public Book findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT *FROM book WHERE id=?;"
            );

            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setPrice(resultSet.getDouble("price"));

                return book;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book findByTitle(String title) {

        Connection connection=jdbConnectionWrapper.getConnection();

        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT *from book where title= ?");

            preparedStatement.setString(1,title);

            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setPrice(resultSet.getDouble("price"));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
