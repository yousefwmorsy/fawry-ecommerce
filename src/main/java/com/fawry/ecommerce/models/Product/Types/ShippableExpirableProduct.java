package com.fawry.ecommerce.models.Product.Types;
import com.fawry.ecommerce.models.Product.Product;

import java.time.LocalDate;

import com.fawry.ecommerce.models.Product.Expirable;
import com.fawry.ecommerce.models.Product.Shippable;

public class ShippableExpirableProduct extends Product implements Expirable, Shippable{
    LocalDate expirationDate;
    double weight;

    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        setExpirationDate(expirationDate);  
        setWeight(weight);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setWeight(double weight) {
        if(weight >= 0) {
            this.weight = weight;
        }
        else {
            throw new IllegalArgumentException("(" + getName() + ") Weight cannot be negative");
        }
    }
}
