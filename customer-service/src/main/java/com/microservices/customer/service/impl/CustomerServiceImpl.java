package com.microservices.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customer.dao.CustomerDAO;
import com.microservices.customer.dao.ProductDAo;
import com.microservices.customer.dao.WishlistDAO;
import com.microservices.customer.dto.WishlistResponse;
import com.microservices.customer.entity.Customer;
import com.microservices.customer.entity.Product;
import com.microservices.customer.entity.WishList;
import com.microservices.customer.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private WishlistDAO dao;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private ProductDAo productDAO;

	@Override
	@Transactional
	public List<WishlistResponse> getWishlistedItems(long userId) {
		return dao.findWishlistsByCustomerId(userId);
	}

	@Override
	@Transactional // Ensure transaction for adding an item
	public void addWishlistItem(long userId, long productId) {
		Customer customer = customerDAO.findById(userId).orElseThrow(() -> new RuntimeException("Customer not found"));
		Product product = productDAO.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		WishList wishList = new WishList();
		wishList.setCustomer(customer);
		wishList.setProduct(product);

		dao.save(wishList); // Save to DB
	}

	@Override
	@Transactional // Ensure transaction for removing an item
	public void removeWishlistItem(long userId, long productId) {
		WishList wishList = dao.findByCustomerIdAndProductId(userId, productId).orElseThrow();
		dao.delete(wishList);
	}
}