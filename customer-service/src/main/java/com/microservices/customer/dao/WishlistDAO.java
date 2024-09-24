package com.microservices.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.customer.entity.WishList;

public interface WishlistDAO extends JpaRepository<WishList, Long> {
	@Query("SELECT wl FROM WishList wl WHERE wl.customer.userId = :userId")
	List<WishList> findByCustomerUserId(@Param("userId") Long userId);

}
