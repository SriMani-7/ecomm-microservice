package com.microservices.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customer.dto.ProductReviewRequest;
import com.microservices.customer.dto.ProductReviewResponse;
import com.microservices.customer.service.ProductReviewService;

@RestController
@RequestMapping("/customers/{userId}/reviews")
public class ProductReviewController {

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping
	public ResponseEntity<List<ProductReviewResponse>> listReviewsByCustomer(@PathVariable Long userId) {
		var reviews = productReviewService.getReviewsByCustomerId(userId);
		return ResponseEntity.ok(reviews);
	}

	@PostMapping
	public ResponseEntity<String> createReview(@PathVariable Long userId, @RequestBody ProductReviewRequest request) {
		productReviewService.saveProductReview(userId, request.getProductId(), request.getReviewContent(),
				request.getRating());
		return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable long reviewId) {
		productReviewService.deleteReview(reviewId);
		return ResponseEntity.ok("Review deleted successfully");
	}
}
