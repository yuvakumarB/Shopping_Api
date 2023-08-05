package org.shoopingKart.ShoopingApi.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dao.MerchantDao;
import org.shoopingKart.ShoopingApi.dto.EmailConfiguration;
import org.shoopingKart.ShoopingApi.dto.Merchant;
import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.exception.InvalidCredentialsException;
import org.shoopingKart.ShoopingApi.exception.idNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;

	@Autowired
	private MailSenderService senderService;

	@Autowired
	private EmailConfiguration configuration;

	public Merchant saveMerchant(Merchant m) {
		configuration.setSubject("Regisration Succesful");
		HashMap<String, String> map = new LinkedHashMap<>();
		map.put("email", m.getEmail());
		map.put("name", m.getName());
		configuration.setText(
				"Hello Mr." + m.getName() + "You have succesfully initiated the registration for our HappyHarvest ");
		configuration.setMerchant(map);
		senderService.sendEmail(configuration);
//		ResponseStructure<Merchant> structure =new ResponseStructure<>();
//		structure.setBody(dao.saveMerchant(m));
//		structure.setMessage("Merchant registered successfully...");
//		structure.setCode(HttpStatus.ACCEPTED.value());
		return dao.saveMerchant(m);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant m) {
		Optional<Merchant> merchant = dao.findMerchantById(m.getId());
		if (merchant.isPresent()) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant found and updated");
			structure.setBody(dao.updateMerchant(m));
			structure.setCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.FOUND);
		}
		throw new idNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Merchant>> findMerchantById(int id) {
		Optional<Merchant> merchant = dao.findMerchantById(id);
		if (merchant.isPresent()) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant found");
			structure.setBody(merchant.get());
			structure.setCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.FOUND);
		}
		throw new idNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchant() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setMessage("List of Merchants");
		structure.setBody(dao.findAllMerchant());
		structure.setCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<String>> deleteMerchant(int id) {
		Optional<Merchant> merchant = dao.findMerchantById(id);
		if (merchant.isPresent()) {
			dao.deleteMerchant(id);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Merchant Found");
			structure.setBody("Merchant Deleted Successfully...");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new idNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> validateMerchant(long phone, String password) {
		Merchant m = dao.validateMerchant(phone, password);
		if (m != null) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Merchant found");
			structure.setBody("Login successful...");
			structure.setCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.FOUND);
		}
		throw new InvalidCredentialsException();
	}

	public ResponseEntity<ResponseStructure<String>> findMerchantByToken(String token) {
		Merchant merch = dao.findMerchantByToken(token);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (merch != null) {
			merch.setToken(null);
			dao.saveMerchant(merch);
			responseStructure.setMessage("verfied");
			responseStructure.setBody("Account Activated");
			responseStructure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

		} else {
			responseStructure.setMessage("Not Found");
			responseStructure.setBody("Account Not Activated");
			responseStructure.setCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);

		}
	}

}
