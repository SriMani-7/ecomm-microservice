package com.microservices.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.customer.dto.ProductReviewResponse;
import com.microservices.customer.entity.ProductReview;

@Repository
public interface ProductReviewDAO extends JpaRepository<ProductReview, Long> {

	@Query("SELECT new com.microservices.customer.dto.ProductReviewResponse(pr.id,pr.product.id, pr.product.title, pr.reviewContent, pr.rating) "
			+ "FROM ProductReview pr WHERE pr.customer.id = :customerId")
	List<ProductReviewResponse> findReviewsByCustomerId(@Param("customerId") long customerId);
}
