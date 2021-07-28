package br.com.zedelivery.microservices.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zedelivery.microservices.exceptions.BadRequestException;
import br.com.zedelivery.microservices.exceptions.EntityNotExistsException;
import br.com.zedelivery.microservices.exceptions.InternalServerErrorException;
import br.com.zedelivery.microservices.exceptions.Messages;
import br.com.zedelivery.microservices.model.entity.Partner;
import br.com.zedelivery.microservices.model.repository.PartnerRepository;

@Service
public class PartnerService {

	@Autowired
	PartnerRepository repository;

	public Partner findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotExistsException(Messages.P_NOT_EXISTS.getMsg()));
	}

	public List<Partner> findAll() {
		final List<Partner> partners = repository.findAll();

		if (partners.isEmpty())
			throw new EntityNotExistsException(Messages.PS_NOT_EXISTS.getMsg());

		return partners;
	}

	@Transactional
	public Partner insert(Partner partner) {
		try {
			if (!partner.containPoint(partner.getAddress()))
				throw new BadRequestException(Messages.INVALID_ADRESS.getMsg());
			return repository.save(partner);

		} catch (Exception e) {
			throw new InternalServerErrorException(Messages.PS_INSERT_FAILED.getMsg() + e.getLocalizedMessage());
		}
	}

}
