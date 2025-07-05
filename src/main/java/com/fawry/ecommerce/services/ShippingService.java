package com.fawry.ecommerce.services;
import com.fawry.ecommerce.models.Cart.*;
import com.fawry.ecommerce.models.Product.*;

public class ShippingService {
    public int shipProducts(Cart cart){
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("No products to ship.");
            return 0;
        }
        double totalWeight = 0.0;
        int count = 0;
        System.out.println("** Shipment notice **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product instanceof Shippable) { //loop through shippable products only
                Shippable shippableProduct = (Shippable) product;
                totalWeight += shippableProduct.getWeight() * item.getQuantity();
                count+= item.getQuantity();
                System.out.println(item.getQuantity() + "x " + product.getName() + "\t" + shippableProduct.getWeight() * item.getQuantity() + "g");
            } 
        }
        System.out.println("Total package weight " + totalWeight/1000 + "kg");

        return count*10; // Assuming shipping cost is 10 per shippable item
    }
}
