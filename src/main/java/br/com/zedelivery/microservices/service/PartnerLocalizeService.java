package br.com.zedelivery.microservices.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import br.com.zedelivery.microservices.exceptions.BadRequestException;
import br.com.zedelivery.microservices.exceptions.EntityNotExistsException;
import br.com.zedelivery.microservices.exceptions.InternalServerErrorException;
import br.com.zedelivery.microservices.exceptions.Messages;
import br.com.zedelivery.microservices.model.entity.Partner;
import br.com.zedelivery.microservices.model.repository.PartnerRepository;
import br.com.zedelivery.microservices.utils.InputValidation;

@Service
public class PartnerLocalizeService {

	@Autowired
	PartnerRepository repository;

	public Partner findCloserPartnerByCoordinates(String lat, String lon) {

		if (InputValidation.isNullOrEmpty(lat) || InputValidation.isNullOrEmpty(lon))
			throw new BadRequestException(Messages.EMPTY_VALUES.getMsg());

		GeometryFactory gFactory = new GeometryFactory();
		Point point = gFactory
				.createPoint(new Coordinate(Double.parseDouble(lon.trim()), Double.parseDouble(lat.trim())));

		List<Partner> partnersOnPoint = repository.findAll().stream().filter(ptn -> ptn.containPoint(point))
				.collect(Collectors.toList());

		Partner closerPartner = null;
		Double minDistance = null;

		if (partnersOnPoint.size() == 0)
			throw new EntityNotExistsException(Messages.PS_NOT_FOUND.getMsg());

		if (partnersOnPoint.size() == 1)
			return partnersOnPoint.get(0);

		try {
			for (Partner ptn : partnersOnPoint) {

				Double distance = ptn.getDistanceToLocalization(point);

				if (null == minDistance || distance.compareTo(minDistance) < 0) {
					minDistance = distance;
					closerPartner = ptn;
				}
			}

			return closerPartner;

		} catch (Exception e) {
			throw new InternalServerErrorException(Messages.PS_FIND_ERROR.getMsg() + e.getLocalizedMessage());
		}
	}

}
