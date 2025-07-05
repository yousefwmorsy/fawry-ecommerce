package com.fawry.ecommerce.models.Product;

public abstract class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
        else {
            this.name = "Unnamed Product";
        }
    }

    public void setPrice(double price) {
        if(price >= 0){
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public void setQuantity(int quantity) {
        if(quantity >= 0){
            this.quantity = quantity;
        }
        else {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
