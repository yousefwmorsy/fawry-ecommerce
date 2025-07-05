package com.fawry.ecommerce.models;
import com.fawry.ecommerce.models.Cart.Cart;

public class Customer {
    String name;
    double balance;
    Cart cart;

    public Customer(String name, double balance) {
        setName(name);
        setBalance(balance);
        setCart(new Cart());
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unnamed Customer";
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }

    
    public void setCart(Cart cart) {
        if (cart != null) {
            this.cart = cart;
        } else {
            throw new IllegalArgumentException("Cart cannot be null");
        }
    }
}
