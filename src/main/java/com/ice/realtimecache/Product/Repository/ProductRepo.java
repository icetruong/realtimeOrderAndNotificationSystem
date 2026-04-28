package com.ice.realtimecache.Product.Repository;

import com.ice.realtimecache.Product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
