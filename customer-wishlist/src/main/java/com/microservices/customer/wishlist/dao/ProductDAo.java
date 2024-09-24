package com.microservices.customer.wishlist.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.customer.wishlist.entity.Product;

public interface ProductDAo extends JpaRepository<Product, Long> {

}
