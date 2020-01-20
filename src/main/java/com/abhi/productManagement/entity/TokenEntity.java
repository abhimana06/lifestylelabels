package com.abhi.productManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="TOKEN")
@Getter
@Setter
public class TokenEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ACCESS_TOKEN",length=400)
	private String accessToken;
	
	@Column(name="STATUS")
	private String tokenStatus;
	

}
