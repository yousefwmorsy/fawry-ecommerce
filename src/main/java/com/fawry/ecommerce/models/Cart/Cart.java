package com.fawry.ecommerce.models.Cart;
import java.util.LinkedList;
import com.fawry.ecommerce.models.Product.Product;


public class Cart {
    LinkedList<CartItem> items;

    public Cart() {
        items = new LinkedList<CartItem>();
    }

    public void add(Product product, int quantity) {       
        try {
            for (CartItem item : items) {
                // Check if the product already exists in the cart
                if (item.getProduct().equals(product)) {
                    item.updateQuantity(quantity);
                    return; // Exit loop after updating quantity
                }
            }
            items.add(new CartItem(product, quantity)); // Add new item to the cart
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding item to cart: " + e.getMessage());
        }
    }

    public void remove(Product product) {
        // Remove the product from the cart if it exists
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public LinkedList<CartItem> getItems() {
        return items;
    }

    public float getTotalPrice() {
        float total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
