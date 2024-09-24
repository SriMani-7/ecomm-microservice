package com.microservices.customer.wishlist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @Column(name = "user_id")  // Ensure this maps to user_id in the database
    private long userId;

    @Column(nullable = false)
    private String name;
}

