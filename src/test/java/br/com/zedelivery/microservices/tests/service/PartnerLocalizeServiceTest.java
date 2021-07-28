package br.com.zedelivery.microservices.tests.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import br.com.zedelivery.microservices.exceptions.BadRequestException;
import br.com.zedelivery.microservices.exceptions.EntityNotExistsException;
import br.com.zedelivery.microservices.model.entity.Partner;
import br.com.zedelivery.microservices.model.repository.PartnerRepository;
import br.com.zedelivery.microservices.service.PartnerLocalizeService;

public class PartnerLocalizeServiceTest {
	

	@InjectMocks
	PartnerLocalizeService service;

	@Mock
	PartnerRepository repository;

	Partner partner;
	List<Partner> partners;

	@Before
	public void setup() {

		partner = buildPartner(new Coordinate(-46.47441029548645, -23.544258235698877), "1553331125841/0001");
		partners = new ArrayList<>();
		partners.add(partner);
	}
	
	public Partner buildPartner(Coordinate address, String document) {
		GeometryFactory gf = new GeometryFactory();
		Point point = gf.createPoint(address);

		Coordinate[] coords = new Coordinate[] { new Coordinate(-46.475815773010254, -23.543854972215986),
				new Coordinate(-46.47586941719055, -23.546776145574036),
				new Coordinate(-46.47333741188049, -23.5472285773942),
				new Coordinate(-46.472575664520264, -23.546127001547458),
				new Coordinate(-46.47233963012695, -23.545920455048698),
				new Coordinate(-46.47209286689758, -23.545812263896046),
				new Coordinate(-46.471813917160034, -23.54578275720259),
				new Coordinate(-46.471771001815796, -23.545704072654374),
				new Coordinate(-46.47147059440613, -23.54574341493437),
				new Coordinate(-46.471813917160034, -23.54411070042177),
				new Coordinate(-46.47239327430725, -23.544071357653248),
				new Coordinate(-46.47287607192993, -23.543884479342033),
				new Coordinate(-46.475815773010254, -23.543854972215986) };

		Polygon[] polygons = new Polygon[] { gf.createPolygon(gf.createLinearRing(coords), null) };

		final MultiPolygon multiPolygon = gf.createMultiPolygon(polygons);

		return Partner.builder().tradingName("Bar do Ze").document("1553331125841/0001").ownerName("Seu Ze")
				.address(point).coverageArea(multiPolygon).build();
	}

	
	@Test
	public void testFindCloserPartnerByCoordinatesOK() {
		Mockito.when(repository.findAll()).thenReturn(partners);
		assertThat(service.findCloserPartnerByCoordinates("-23.544258235698877", "-46.47441029548645")).isNotNull();
	}

	@Test
	public void testFindCloserPartnerByCoordinatesOK2() {
		partners.add(buildPartner(new Coordinate(-46.47441029548648, -23.544258235698878), "1553331125841/0001"));
		Mockito.when(repository.findAll()).thenReturn(partners);
		assertThat(service.findCloserPartnerByCoordinates("-23.544258235698877", "-46.47441029548645")).isNotNull();
	}

	@Test(expected = EntityNotExistsException.class)
	public void testFindCloserPartnerByCoordinatesNOK() {
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
		service.findCloserPartnerByCoordinates("-22.544258235698877", "-44.47441029548645");
	}

	@Test(expected = BadRequestException.class)
	public void testFindCloserPartnerByCoordinatesNOK2() {
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
		service.findCloserPartnerByCoordinates("", null);
	}

}
