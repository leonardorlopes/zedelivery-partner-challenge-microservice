package br.com.zedelivery.microservices.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zedelivery.microservices.model.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long>{

}
