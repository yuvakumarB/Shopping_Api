package org.shoopingKart.ShoopingApi.repository;

import org.shoopingKart.ShoopingApi.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
