package com.fawry.ecommerce;
import java.time.LocalDate;

import com.fawry.ecommerce.factories.ProductFactory;
import com.fawry.ecommerce.models.*;
import com.fawry.ecommerce.models.Cart.*;
import com.fawry.ecommerce.models.Product.*;
import com.fawry.ecommerce.services.*;

public class Main {
    public static void checkout(Customer customer, Cart cart) {
        CheckoutService checkoutService = new CheckoutService();
        try {
            checkoutService.checkout(customer, cart);
        } catch (IllegalArgumentException e) {
            System.out.println("Checkout failed for " +customer.getName()+ ": " + e.getMessage());
        }
        System.out.println();
    }
    public static void main(String[] args) {

        /*
            Code Example 1:
            - Covers adding products to the cart, checking for expiration and shipping.
            - Tests checkout with insufficient balance.
            - Tests checkout with sufficient balance.
            - Tests adding expired products.
            - Tests adding products that are not shippable or expirable.
            - Tests adding products that are both shippable and expirable.
            - Tests adding products that are only shippable.
            - Tests adding products by 2 different customers.
            - Tests adding a product twice.
         */

        Customer customer = new Customer("Yousef", 440);
        Customer customer2 = new Customer("Joe", 4500);
        Cart cart = customer.getCart();
        Cart cart2 = customer2.getCart();


        Product cheese = ProductFactory.createProduct("Cheese", 100, 10, LocalDate.of(2025, 7, 30), 200);
        Product biscuit = ProductFactory.createProduct("Biscuits", 150, 10, LocalDate.of(2024, 12, 31), 700);
        Product tv = ProductFactory.createProduct("TV", 2000, 5, 5000);
        Product scratchCard = ProductFactory.createProduct("Scratch Card", 20, 5);


        cart.add(cheese, 1); //available cheese, 10 - 1 = 9 (Shippable & Expirable)
        cart.add(cheese, 2); //available cheese, 9 - 2 = 7 (Shippable & Expirable)
        cart.add(biscuit, 1); //biscuits not added due to being expired (Shippable & Expirable)
        cart.add(tv, 1); // (Shippable only)
        cart.add(scratchCard, 1); // (Basic Product, no expiration or shipping)
        checkout(customer, cart); // checkout unsuccessful due to insufficient balance
        
        cart2.add(cheese, 5); //available cheese, 7 - 5 = 2 (Shippable & Expirable)
        cart2.add(biscuit, 3); //biscuits not added due to being expired (Shippable & Expirable)
        cart2.add(tv, 1); // (Shippable only)
        cart2.add(scratchCard, 3); // (Basic Product, no expiration or shipping)
        cart2.add(cheese, 2); //available cheese, 2 - 2 = 0 (Shippable & Expirable)
        checkout(customer2, cart2); // checkout successful, cart is reset

        /*
            Code Example 2:
            - Covers adding products to the cart, checking for quantity  and shipping.
            - Tests checkout with sufficient balance.
            - Tests adding products that are only expirable.
            - Tests adding a quantity of products that exceeds the available stock.
            - Tests that the cart is cleared after checkout.
        */

        Product milk = ProductFactory.createProduct("Milk", 40, 10, LocalDate.of(2025, 7, 20), 500);
        Product iceCream = ProductFactory.createProduct("Ice Cream", 250, 10, LocalDate.of(2025, 12, 31));
        Product giftCard = ProductFactory.createProduct("Gift Card", 800, 3);

        cart2.add(milk, 2); //available milk, 10 - 2 = 8 (Shippable & Expirable)
        cart2.add(iceCream, 1); //available ice cream, 10 - 1 = 9 (Expirable)
        cart2.add(giftCard, 4); //not added due to insufficient stock (Basic Product, no expiration or shipping)
        cart2.add(giftCard, 2); //available gift card, 3 - 2 = 1 (Basic Product, no expiration or shipping)
        cart2.add(giftCard, 2); //not added due to insufficient stock (Basic Product, no expiration or shipping)

        checkout(customer2, cart2); //checkout unsuccessful due to insufficient balance


        /* 
            Code Example 3: 
            - Covers checkout of an empty cart.
        */

        Customer customer3 = new Customer("Ahmed", 100);
        Cart cart3 = customer3.getCart();

        checkout(customer3, cart3); // checkout unsuccessful due to empty cart
    }
}