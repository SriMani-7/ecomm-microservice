package com.microservices.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customer.dao.CustomerDAO;
import com.microservices.customer.dao.ProductDAo;
import com.microservices.customer.dao.ProductReviewDAO;
import com.microservices.customer.dto.ProductReviewResponse;
import com.microservices.customer.entity.Customer;
import com.microservices.customer.entity.Product;
import com.microservices.customer.entity.ProductReview;
import com.microservices.customer.service.ProductReviewService;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductReviewDAO productReviewDAO;

	@Autowired
	private CustomerDAO customerDAO; // Assuming you have a DAO for Customer

	@Autowired
	private ProductDAo productDAO; // Assuming you have a DAO for Product

	@Override
	public List<ProductReviewResponse> getReviewsByCustomerId(long userId) {
		return productReviewDAO.findReviewsByCustomerId(userId);
	}

	@Override
	public void saveProductReview(long userId, long productId, String reviewContent, int rating) {
		ProductReview review = new ProductReview();

		// Fetch Customer and Product
		Customer customer = customerDAO.findById(userId).orElseThrow(() -> new RuntimeException("Customer not found"));
		Product product = productDAO.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

		// Set the entities
		review.setCustomer(customer);
		review.setProduct(product);
		review.setReviewContent(reviewContent);
		review.setRating(rating);

		productReviewDAO.save(review);
	}

	@Override
	public void deleteReview(long id) {
		productReviewDAO.deleteById(id);
	}
}
