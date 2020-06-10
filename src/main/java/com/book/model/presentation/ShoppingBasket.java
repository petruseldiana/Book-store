package com.book.model.presentation;

import java.util.List;

public class ShoppingBasket {
    private Long id;
    private User user;
    private boolean active;
    private String name;
    private List<ShoppingBasketBook> shoppingBasketBooks;

    public ShoppingBasket() {
    }

    public ShoppingBasket(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingBasketBook> getShoppingBasketBooks() {
        return shoppingBasketBooks;
    }

    public void setShoppingBasketBooks(List<ShoppingBasketBook> shoppingBasketBooks) {
        this.shoppingBasketBooks = shoppingBasketBooks;
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", active=" + active +
                ", name='" + name + '\'' +

                '}';
    }
}
