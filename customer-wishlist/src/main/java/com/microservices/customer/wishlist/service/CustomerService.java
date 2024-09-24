package com.microservices.customer.wishlist.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservices.customer.wishlist.entity.Product;
import com.microservices.customer.wishlist.entity.WishList;

public interface CustomerService {

	List<Product> getItems(long userId);

	void addWishlistItem(long userId, long productId);

	void removeWishlistItem(long userId, long productId);

}
