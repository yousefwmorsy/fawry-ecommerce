package com.fawry.ecommerce.models.Cart;

import com.fawry.ecommerce.models.Product.Expirable;
import com.fawry.ecommerce.models.Product.Product;

public class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        setProduct(product);
        setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(product instanceof Expirable && ((Expirable) product).isExpired()){
            throw new IllegalArgumentException("Cannot add expired product (" + product.getName() + ") to cart");
        }
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if(product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient product (" + product.getName() + ") quantity in stock, only " + product.getQuantity() + " available");
        }
        else if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        else {
            this.quantity = quantity;
        }
        product.setQuantity(product.getQuantity() - quantity); // Reduce product stock
    }

    public void updateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        else if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient product (" + product.getName() + ") quantity in stock, only " + product.getQuantity() + " available");
        }
        else{
            this.quantity += quantity;
            product.setQuantity(product.getQuantity() - quantity); // Reduce product stock
        }
    }
}
