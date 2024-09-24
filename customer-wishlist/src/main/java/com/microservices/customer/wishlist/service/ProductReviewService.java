package com.microservices.customer.wishlist.service;

import java.util.List;

import com.microservices.customer.wishlist.entity.ProductReview;


public interface ProductReviewService {

	

	List<ProductReview> getReviewsByCustomerId(long userId);

	void saveProductReview(long userId, long productId, String reviewContent, int rating);

	void deleteReview(long id);
}
