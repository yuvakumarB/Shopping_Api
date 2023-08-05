package org.shoopingKart.ShoopingApi.dao;

import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dto.Product;
import org.shoopingKart.ShoopingApi.dto.User;
import org.shoopingKart.ShoopingApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ProductDao {

	 @Autowired
	 private ProductRepository repository;
	 
	 public Product saveProduct(Product product) {
		 return repository.save(product);
	 }
	 
	 public Product updateProduct(Product product) {
		 return repository.save(product);
	 }
	 
	 public Optional<Product> getProduct(int id) {
//			return repository.findById(id).get();
			return repository.findById(id);
			
//			Optional<Product> recuser=repository.findById(id);
//			if(recuser.isEmpty()) {
//				return null;
//				
//			}
//			else
//				return recuser.get();
	//
		}
	 
		public void deleteProduct(int id) {
			repository.deleteById(id);
		}
		public List<Product> findAll(){
			return repository.findAll();
		}
	    
}
