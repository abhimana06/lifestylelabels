package com.abhi.productManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends BaseEntity{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="TITLE")
	private String title;
	
	@Lob
	@Column(name = "PRODUCT_IMAGE_URL")
	private String imgUrl;

	@ManyToOne(fetch=FetchType.LAZY)
	private CategoryEntity category;
	
	@Column(name="PRICE")
	private Long price;
	
	@Column(name="MSRP")
	private Long msrp;
	
	@Column(name="IS_AVAILABLE")
	private boolean isAvailable;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MerchantEntity merchant;
	
	@Column(name="DESCRIPTION")
	private String description;

	

	
	
}
