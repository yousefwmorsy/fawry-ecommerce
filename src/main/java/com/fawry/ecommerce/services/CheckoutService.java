package com.fawry.ecommerce.services;
import com.fawry.ecommerce.models.Cart.Cart;
import com.fawry.ecommerce.models.Cart.CartItem;
import com.fawry.ecommerce.models.Customer;
public class CheckoutService {
    public void checkout(Customer customer, Cart cart) {
        if(customer == null || cart == null) {
            throw new IllegalArgumentException("Customer and cart cannot be null");
        }
        else if(cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        else {
            //update customer balance to reflect the purchase
            
            //call the shipping service to ship the products and calculate shipping cost
            ShippingService shippingService = new ShippingService();
            float shippingCost = shippingService.shipProducts(cart);
            System.out.println();
            
            if(customer.getBalance() < cart.getTotalPrice() + shippingCost) {
                //if the customer does not have enough balance, print the receipt and throw an exception
                printReciept(cart, shippingCost);
                throw new IllegalArgumentException("Insufficient balance for checkout, only " + customer.getBalance() + " available.");
            }
            else {
                //deduct the total price and shipping cost from the customer's balance
                customer.setBalance(customer.getBalance() - (cart.getTotalPrice() + shippingCost));
                //print the receipt
                printReciept(cart, shippingCost);
                //clear the cart after checkout
                cart.clear();
                System.out.println("Checkout successful for "+ customer.getName() +". Remaining balance: " + customer.getBalance());
            }
        }
    }    

    public void printReciept(Cart cart, float shippingCost) {
        System.out.println("** Checkout receipt **");
        for(CartItem item : cart.getItems()) {
            System.out.println( item.getQuantity() + "x " + item.getProduct().getName()  + "\t" + item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        double total = cart.getTotalPrice() + shippingCost;
        System.out.println("Subtotal" + "\t" + (total - shippingCost));
        System.out.println("Shipping" + "\t" + shippingCost);
        System.out.println("Amount" + "\t\t" + total);
    }
}
