package com.microservices.customer.wishlist.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.customer.wishlist.entity.Customer;
import com.microservices.customer.wishlist.entity.WishList;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
	
}
