package com.fawry.ecommerce.factories;

import com.fawry.ecommerce.models.Product.Types.*;

import java.time.LocalDate;

import com.fawry.ecommerce.models.Product.*;

public class ProductFactory {
    
    // Factory methods to create different types of products

    public static Product createProduct(String name, double price, int quantity) {
        return new BasicProduct(name, price, quantity);
    }
    public static Product createProduct(String name, double price, int quantity, LocalDate expirationDate) {
        return new ExpirableProduct(name, price, quantity, expirationDate);
    }
    public static Product createProduct(String name, double price, int quantity, double weight) {
        return new ShippableProduct(name, price, quantity, weight);
    }
    public static Product createProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        return new ShippableExpirableProduct(name, price, quantity, expirationDate, weight);   
    }
}
