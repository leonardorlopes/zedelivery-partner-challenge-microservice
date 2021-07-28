package br.com.zedelivery.microservices.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "partner")
public class Partner implements DefaultEntity, DataEntityTransfer {

	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "Trading name can't be empty")
	@NotBlank(message = "Trading can't be blank")
	@NotNull
	private String tradingName;

	@NotEmpty(message = "Owner name can't be empty")
	@NotBlank(message = "Owner can't be blank")
	@NotNull
	private String ownerName;

	@NotEmpty(message = "Document can't be empty")
	@NotBlank(message = "Document can't be blank")
	@NotNull
	@Column(unique = true)
	private String document;

	@NotNull
	private MultiPolygon coverageArea;

	@NotNull
	private Point address;

	public boolean containPoint(Point point) {
		return coverageArea.contains(point);
	}

	public double getDistanceToLocalization(Point point) {
		return address.distance(point);
	}

	public Point getAddress() {
		return address;
	}

	public void setAddress(Point address) {
		this.address = address;
	}
}
