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

import com.microservices.customer.entity.ProductReview;
import com.microservices.customer.service.ProductReviewService;

@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping // http://localhost:8080/reviews?userId=2
	public ResponseEntity<List<ProductReview>> listReviewsByCustomer(@RequestParam Long userId) {
		List<ProductReview> reviews = productReviewService.getReviewsByCustomerId(userId);
		return ResponseEntity.ok(reviews); // Returns list of reviews with 200 OK
	}

	// Create a new product review using RequestParam
	@PostMapping // http://localhost:8080/reviews?userId=101&productId=4&reviewContent=Great
					// product!&rating=5
	public ResponseEntity<String> createReview(@RequestParam Long userId, @RequestParam long productId,
			@RequestParam String reviewContent, @RequestParam int rating) {
		productReviewService.saveProductReview(userId, productId, reviewContent, rating);
		return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
	}

	// Delete a review by its id using PathVariable
	@DeleteMapping("/{reviewId}") // http://localhost:8080/reviews/5
	public ResponseEntity<String> deleteReview(@PathVariable long reviewId) {
		productReviewService.deleteReview(reviewId);
		return ResponseEntity.ok("Review deleted successfully");
	}
}
