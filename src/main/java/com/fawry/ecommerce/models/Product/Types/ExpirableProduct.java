package com.fawry.ecommerce.models.Product.Types;
import com.fawry.ecommerce.models.Product.Product;
import com.fawry.ecommerce.models.Product.Expirable;
import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable{
    LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        setExpirationDate(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
