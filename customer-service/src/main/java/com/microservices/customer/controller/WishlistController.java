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

import com.microservices.customer.entity.Product;
import com.microservices.customer.service.CustomerService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private CustomerService service;

	@GetMapping("/{userId}") // http://localhost:8080/wishlist/105
	public ResponseEntity<List<Product>> wishlistView(@PathVariable Long userId) {
		List<Product> items = service.getItems(userId); // Assuming getItems returns List<Product>
		return ResponseEntity.ok(items);
	}

	@PostMapping("/add") // http://localhost:8080/wishlist/add?userId=101&productId=1
	public ResponseEntity<String> addProduct(@RequestParam Long userId, @RequestParam Long productId) {
		// Assuming the user must be authenticated (you might need to check user
		// validity in your service)
		service.addWishlistItem(userId, productId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist");
	}

	@DeleteMapping("/remove") // http://localhost:8080/wishlist/remove?userId=101&productId=1
	public ResponseEntity<String> deleteProduct(@RequestParam Long userId, @RequestParam Long productId) {
		service.removeWishlistItem(userId, productId);
		return ResponseEntity.ok("Product removed from wishlist");
	}
}
