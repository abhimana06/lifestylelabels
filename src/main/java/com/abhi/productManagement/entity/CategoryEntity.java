package com.abhi.productManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CTGRY_NAME")
	private String categoryName;
	
	@Column(name="CTGRY_ONE")
	private String categoryOne;
	
	@Column(name="CTGRY_TWO")
	private String categoryTwo;
	
	@Column(name="CTGRY_THREE")
	private String categoryThree;
	
	@Column(name="CTGRY_SUBTYPE1")
	private String categorySubType1;
	
	@Column(name="CTGRY_SUBTYPE2")
	private String categorySubType2;
	
	@Column(name="CTGRY_SUBTYPE3")
	private String categorySubType3;
	

}
