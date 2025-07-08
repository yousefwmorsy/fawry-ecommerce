# Fawry E-Commerce System

## Overview
This project is a simple e-commerce system implemented in Java, following object-oriented design principles. It adding products, shopping cart operations, creating customers, checkout with shipping and expiration logic, and receipt generation. The project is structured as a Maven project for easy dependency management and future expandability.

## Project Structure
```
fawry-ecommerce/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           └── com/fawry/ecommerce/
│               ├── Main.java
│               ├── factories/ProductFactory.java
│               ├── models/
│               │   ├── Customer.java
│               │   ├── Cart/Cart.java
│               │   ├── Cart/CartItem.java
│               │   ├── Product/Product.java
│               │   ├── Product/Expirable.java
│               │   ├── Product/Shippable.java
│               │   └── Product/Types/
│               │       ├── BasicProduct.java
│               │       ├── ExpirableProduct.java
│               │       ├── ShippableProduct.java
│               │       └── ShippableExpirableProduct.java
│               └── services/
│                   ├── CheckoutService.java
│                   └── ShippingService.java
└── target/
```

## Setup Instructions
1. **Clone the repository**
   ```sh
   git clone https://github.com/yousefwmorsy/fawry-ecommerce
   ```
2. **Build the project using Maven**
   ```sh
   mvn clean compile
   ```
3. **Run the program**
   ```sh
   mvn exec:java
   ```


## Class Hierarchy & Design

### Product Hierarchy
- **Product (abstract)**: Base class for all products. Contains common fields (`name`, `price`, `quantity`).
- **BasicProduct**: Simple product, no expiration or shipping.
- **ExpirableProduct**: Extends `Product`, implements `Expirable` interface (adds `expirationDate`).
- **ShippableProduct**: Extends `Product`, implements `Shippable` interface (adds `weight`).
- **ShippableExpirableProduct**: Extends `Product`, implements both `Expirable` and `Shippable` (has both `expirationDate` and `weight`).
- **Expirable (interface)**: Declares methods for expiration logic.
- **Shippable (interface)**: Declares methods for shipping logic.

### Cart & Customer
- **Cart**: Holds a list of `CartItem` objects. Supports adding/removing products, calculating total price, and clearing the cart.
- **CartItem**: Represents a product and its quantity in the cart. Handles stock reduction and validation for expiry.
- **Customer**: Has a name, balance, and a `Cart`.

### Services
- **CheckoutService**: Handles the checkout process, including balance checks, shipping, and receipt printing.
- **ShippingService**: Calculates shipping cost and prints shipping details for shippable products.

### Factory
- **ProductFactory**: Provides static methods to create different product types based on the parameters provided.

## Program Flow
1. Customers are created with a name and balance.
2. Products are created using the `ProductFactory`.
3. Products are added to the customer's cart.
4. The checkout process is initiated, which:
   - Validates the cart and customer balance.
   - Ships shippable products and calculates shipping cost.
   - Prints a receipt.
   - Deducts the total amount from the customer's balance.
   - Clears the cart.

## Test Cases in Main.java
The `Main.java` file contains code examples that demonstrate the system's functionality:


## Code Example #1
```java
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
```

## Sample Output #1
```
Error adding item to cart: Cannot add expired product (Biscuits) to cart
** Shipment notice **
3x Cheese       600.0g
1x TV   5000.0g
Total package weight 5.6kg

** Checkout receipt **
3x Cheese       300.0
1x TV   2000.0
1x Scratch Card 20.0
----------------------
Subtotal        2320.0
Shipping        40.0
Amount          2360.0
Checkout failed for Yousef: Insufficient balance for checkout, only 440.0 available.       

Error adding item to cart: Cannot add expired product (Biscuits) to cart
** Shipment notice **
7x Cheese       1400.0g
1x TV   5000.0g
Total package weight 6.4kg

** Checkout receipt **
7x Cheese       700.0
1x TV   2000.0
3x Scratch Card 60.0
----------------------
Subtotal        2760.0
Shipping        80.0
Amount          2840.0
Checkout successful for Joe. Remaining balance: 1660.0
```

## Code Example #2 (Example #1 Continued)
```java
Product milk = ProductFactory.createProduct("Milk", 40, 10, LocalDate.of(2025, 7, 20), 500);
Product iceCream = ProductFactory.createProduct("Ice Cream", 250, 10, LocalDate.of(2025, 12, 31));
Product giftCard = ProductFactory.createProduct("Gift Card", 800, 3);

cart2.add(milk, 2); //available milk, 10 - 2 = 8 (Shippable & Expirable)
cart2.add(iceCream, 1); //available ice cream, 10 - 1 = 9 (Expirable)
cart2.add(giftCard, 4); //not added due to insufficient stock (Basic Product, no expiration or shipping)
cart2.add(giftCard, 2); //available gift card, 3 - 2 = 1 (Basic Product, no expiration or shipping)
cart2.add(giftCard, 2); //not added due to insufficient stock (Basic Product, no expiration or shipping)

checkout(customer2, cart2); //checkout unsuccessful due to insufficient balance
```

## Sample Output #2
```
Error adding item to cart: Insufficient product (Gift Card) quantity in stock, only 3 available
Error adding item to cart: Insufficient product (Gift Card) quantity in stock, only 1 available
** Shipment notice **
2x Milk 1000.0g
Total package weight 1.0kg

** Checkout receipt **
2x Milk 80.0
1x Ice Cream    250.0
2x Gift Card    1600.0
----------------------
Subtotal        1930.0
Shipping        20.0
Amount          1950.0
Checkout failed for Joe: Insufficient balance for checkout, only 1660.0 available.
```

## Code Example #3 
```java 
Customer customer3 = new Customer("Ahmed", 100);
Cart cart3 = customer3.getCart();

checkout(customer3, cart3); // checkout unsuccessful due to empty cart
```
## Sample Output #3
```
Checkout failed for Ahmed: Cart is empty
```

## Screenshot
![image](https://github.com/user-attachments/assets/e7415110-11d6-4326-9b59-59de581eb8a2)


## Notes
- Expired products and products with insufficient stock are not added to the cart.
- The receipt and shipping details are printed to the console.
- The system demonstrates OOP principles and is easily extensible for more features, as SOLID principles and the Factory Design Pattern are used.
