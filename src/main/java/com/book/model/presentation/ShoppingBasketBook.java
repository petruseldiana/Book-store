package com.book.model.presentation;

public class ShoppingBasketBook {
    private Long id;
    private Long shoppingBasketId;
    private Book book;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingBasketId() {
        return shoppingBasketId;
    }

    public void setShoppingBasketId(Long shoppingBasketId) {
        this.shoppingBasketId = shoppingBasketId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingBasketItem{" +
                "id=" + id +
                ", shoppingBasketId=" + shoppingBasketId +
                ", book=" + book.toString() +
                ", quantity=" + quantity +
                '}';
    }
}
