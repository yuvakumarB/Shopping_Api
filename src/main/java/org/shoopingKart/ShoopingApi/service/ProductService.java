package org.shoopingKart.ShoopingApi.service;

import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dao.ProductDao;
import org.shoopingKart.ShoopingApi.dto.Product;
import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.exception.idNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
        @Autowired
        private ProductDao dao;
        
        public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product){
    		product=dao.saveProduct(product);
    		ResponseStructure<Product> structure=new ResponseStructure<Product>();
    		structure.setBody(product);
    		structure.setMessage("Product save success");
    	    structure.setCode(HttpStatus.ACCEPTED.value());
    		return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.ACCEPTED);
    	}
       
    	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
     		ResponseStructure<Product> s=new ResponseStructure<Product>();
       	 s.setBody(dao.saveProduct(product));
       	 s.setMessage("Product updated succesfully");
       	 s.setCode(HttpStatus.ACCEPTED.value());
    	return new ResponseEntity<ResponseStructure<Product>>(s,HttpStatus.ACCEPTED);
     	}
     	
     	public ResponseEntity<ResponseStructure<Product>> getProduct(int id) {
     		ResponseStructure<Product> s=new ResponseStructure<Product>();
     		Optional<Product>product=dao.getProduct(id);
     		if(product!=null) {
     			 s.setBody(product.get());
     		   	 s.setMessage("Product is Found");
     		   	 s.setCode(HttpStatus.FOUND.value());
     		   	 return new ResponseEntity<ResponseStructure<Product>>(s,HttpStatus.FOUND);
     		}
     		else {
     			throw new idNotFoundException();
     		}
     		
     		
     	}
     	
     	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
     		ResponseStructure<String> s=new ResponseStructure<>();
     		Optional<Product> product=dao.getProduct(id);
     		if(product!=null) {
     			dao.deleteProduct(id);
     			s.setBody("Product is Found");
     			s.setMessage("Product Delete Succesfully");
     			s.setCode(HttpStatus.FOUND.value());
     			return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FOUND);
     		}
     		else {
     			s.setBody("Product is Not_Found");
     			s.setMessage("Product Unable to Delete");
     			s.setCode(HttpStatus.NOT_FOUND.value());
     			return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
     		}
     		
     	}
     	public ResponseEntity<ResponseStructure<List<Product>>> findAll(){
     		ResponseStructure<List<Product>> s=new  ResponseStructure<List<Product>>();
     		s.setBody(dao.findAll());
     		s.setMessage("Found The All Product");
     		s.setCode(HttpStatus.OK.value());
     		return new ResponseEntity<ResponseStructure<List<Product>>>(s,HttpStatus.OK) ;
     	}
     	
//     	public ResponseEntity<ResponseStructure<String>> validateProduct(String email,String password){
//     		Product Product=dao.validateProduct(email, password);
//     		if(Product != null) {
//     			ResponseStructure<String> structure=new ResponseStructure<String>();
//     			structure.setMessage(Product.getName()+" "+"is found");
//     			structure.setBody(Product.getName()+" "+"Login successfully....");
//     			structure.setCode(HttpStatus.FOUND.value());
//     			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.FOUND);
//     		}
//     		throw new InvalidCredentialsException();
//   
//     	}
 
}
