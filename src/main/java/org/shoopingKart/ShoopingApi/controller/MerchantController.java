package org.shoopingKart.ShoopingApi.controller;

import java.util.List;

import org.shoopingKart.ShoopingApi.dto.Merchant;
import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping("/merchant")
	public Merchant saveMerchant(@RequestBody Merchant m) {
		return service.saveMerchant(m);
	}

	@PutMapping("/merchant")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant m) {
		return service.updateMerchant(m);
	}

	@GetMapping("/merchant/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findMerchantById(@PathVariable int id) {
		return service.findMerchantById(id);
	}

	@GetMapping("/merchant")
	public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchant() {
		return service.findAllMerchant();
	}

	@DeleteMapping("/merchant/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteMerchantById(@PathVariable int id) {
		return service.deleteMerchant(id);
	}

	@GetMapping("/merchant/verfiy-merchat")
	public ResponseEntity<ResponseStructure<String>> validateMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return service.validateMerchant(phone, password);
	}

}
