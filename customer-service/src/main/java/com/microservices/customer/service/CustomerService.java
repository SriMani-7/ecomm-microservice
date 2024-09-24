package com.microservices.customer.service;

import java.util.List;

import com.microservices.customer.entity.Product;

public interface CustomerService {

	List<Product> getItems(long userId);

	void addWishlistItem(long userId, long productId);

	void removeWishlistItem(long userId, long productId);

}
