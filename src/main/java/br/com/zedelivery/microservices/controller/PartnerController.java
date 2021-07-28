package br.com.zedelivery.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zedelivery.microservices.model.entity.Partner;
import br.com.zedelivery.microservices.service.PartnerLocalizeService;
import br.com.zedelivery.microservices.service.PartnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/v1/partner")
@Api(value = "Partner")
public class PartnerController {

	@Autowired
	PartnerService service;
	
	@Autowired
	PartnerLocalizeService localizeService;

	@ApiOperation("Lista todos os parceiros.")
	@GetMapping()
	public ResponseEntity<List<Partner>> findAll() {
		List<Partner> partners = service.findAll();
		return new ResponseEntity<>(partners, HttpStatus.OK);
	}

	@ApiOperation("Lista o parceiro através do id.")
	@GetMapping("/id/{id}")
	public ResponseEntity<Partner> findById(@PathVariable Long id) {
		Partner partner = service.findById(id);
		return new ResponseEntity<>(partner, HttpStatus.OK);
	}

	@ApiOperation("Lista o parceiro mais próximo através das coordenadas.")
	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<Partner> findByCoordinates(@PathVariable String latitude, @PathVariable String longitude) {
		Partner partner = localizeService.findCloserPartnerByCoordinates(latitude, longitude);
		return new ResponseEntity<>(partner, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation("Cria um novo parceiro")
	public ResponseEntity<Partner> create(@RequestBody Partner partner) {
		Partner newPartner = service.insert(partner);
		return new ResponseEntity<>(newPartner, HttpStatus.OK);
	}
}
