package com.fawry.ecommerce.models.Product.Types;

import com.fawry.ecommerce.models.Product.Product;
import com.fawry.ecommerce.models.Product.Shippable;

public class ShippableProduct extends Product implements Shippable{
    double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight >= 0) {
            this.weight = weight;
        }
        else {
            throw new IllegalArgumentException("(" + getName() + ")Weight cannot be negative");
        }
    }
}
