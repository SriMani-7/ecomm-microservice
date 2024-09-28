package com.microservices.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customer.dto.WishlistResponse;
import com.microservices.customer.service.CustomerService;

@RestController
@RequestMapping("/customers/{userId}/wishlist")
public class WishlistController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public List<WishlistResponse> wishlistView(@PathVariable Long userId) {
		return service.getWishlistedItems(userId);
	}

	@PostMapping
	public ResponseEntity<String> addProduct(@PathVariable Long userId, @RequestParam Long productId) {
		service.addWishlistItem(userId, productId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist");
	}

	@DeleteMapping
	public ResponseEntity<String> deleteProduct(@PathVariable Long userId, @RequestParam Long productId) {
		service.removeWishlistItem(userId, productId);
		return ResponseEntity.ok("Product removed from wishlist");
	}
}
