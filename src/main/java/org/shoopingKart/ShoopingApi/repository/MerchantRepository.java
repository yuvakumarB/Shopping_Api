package org.shoopingKart.ShoopingApi.repository;

import org.shoopingKart.ShoopingApi.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	Merchant validate(long phone,String password); 
	
	public Merchant findMerchantByToken(String token);
}
