package org.shoopingKart.ShoopingApi.controller;

import java.util.List;

import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.dto.Product;
import org.shoopingKart.ShoopingApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	
	@PostMapping("/product")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product){
		return service.saveProduct(product);
	}
	@PutMapping("/product")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProduct(@PathVariable int id) {
		return service.getProduct(id);

	}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

	@GetMapping("/product")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return service.findAll();
	}
	
}
