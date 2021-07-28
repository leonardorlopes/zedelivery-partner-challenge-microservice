package br.com.zedelivery.microservices.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public interface DefaultEntity {

	@ApiModelProperty(hidden = true)
	Long getId();

	void setId(Long id);

	@JsonIgnore
	default String getIdAsString() {
		return getId().toString();
	}

}
