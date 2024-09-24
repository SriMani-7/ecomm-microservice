package com.microservices.customer.wishlist.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.microservices.customer.wishlist.dao.CustomerDAO;
import com.microservices.customer.wishlist.dao.ProductDAo;
import com.microservices.customer.wishlist.dao.WishlistDAO;
import com.microservices.customer.wishlist.entity.Customer;
import com.microservices.customer.wishlist.entity.Product;
import com.microservices.customer.wishlist.entity.WishList;
import com.microservices.customer.wishlist.service.CustomerService;

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
    public List<Product> getItems(long userId) {
        List<WishList> wishLists = dao.findByCustomerUserId(userId);
        return wishLists.stream()
                .map(wl -> productDAO.findById(wl.getProductId()).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // Ensure transaction for adding an item
    public void addWishlistItem(long userId, long productId) {
        Customer customer = customerDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        WishList wishList = new WishList();
        wishList.setCustomer(customer);
        wishList.setProductId(productId);

        dao.save(wishList); // Save to DB
    }

    @Override
    @Transactional // Ensure transaction for removing an item
    public void removeWishlistItem(long userId, long productId) {
        WishList wishList = dao.findByCustomerUserId(userId)
                .stream()
                .filter(wl -> wl.getProductId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Wishlist item not found"));

        dao.delete(wishList); // Delete from DB
    }
}