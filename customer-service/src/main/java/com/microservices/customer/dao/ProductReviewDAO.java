package com.microservices.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.customer.entity.ProductReview;

@Repository
public interface ProductReviewDAO extends JpaRepository<ProductReview, Long> {
	// Change from findByCustomerId to findByCustomer_UserId
	List<ProductReview> findByCustomer_UserId(long userId);

	// You can also add this method to fetch by product
	List<ProductReview> findByProduct_Id(long productId);
}
