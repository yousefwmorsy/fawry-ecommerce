package com.fawry.ecommerce.models.Product;

import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpirationDate();
    void setExpirationDate(LocalDate expirationDate);
    
    default boolean isExpired() {
        return LocalDate.now().isAfter(getExpirationDate());
    }
} 