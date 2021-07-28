package br.com.zedelivery.microservices.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zedelivery.microservices.model.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long>{
	
	Optional<Partner> findByDocument(String document);		
	
}
