package org.shoopingKart.ShoopingApi.dao;

import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dto.Merchant;
import org.shoopingKart.ShoopingApi.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;
	
	public Merchant saveMerchant(Merchant m)
	{
		return repository.save(m);
	}
	
	public Merchant updateMerchant(Merchant m)
	{
		return repository.save(m);
	}
	
	public Optional<Merchant> findMerchantById(int id)
	{
		return repository.findById(id);
	}
	
	public List<Merchant> findAllMerchant()
	{
		return repository.findAll();
	}
	
	public void deleteMerchant(int id)
	{
		repository.deleteById(id);
	}
	
	public Merchant validateMerchant(long phone,String password)
	{
		return repository.validate(phone, password);
	}
	 public Merchant findMerchantByToken(String token) {
		 return repository.findMerchantByToken(token);
	 }

}
