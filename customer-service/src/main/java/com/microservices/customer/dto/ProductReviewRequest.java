package com.microservices.customer.dto;

import lombok.Data;

@Data
public class ProductReviewRequest {
	private long productId;
	private String reviewContent;
	private int rating;
}
