package com.microservices.customer.service;

import java.util.List;

import com.microservices.customer.dto.ProductReviewResponse;

public interface ProductReviewService {

	List<ProductReviewResponse> getReviewsByCustomerId(long userId);

	void saveProductReview(long userId, long productId, String reviewContent, int rating);

	void deleteReview(long id);
}
