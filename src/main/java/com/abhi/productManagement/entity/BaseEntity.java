package com.abhi.productManagement.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

	
	@Column(name="CREATED_BY")
	protected  String createdBy;
	
	@Column(name="MODIFIED_BY")
	protected  String modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	protected  Date createdDate=new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name="MODIFIED_DATE")
	protected  Date modifiedDate;
	
	@Column(name="IS_ACTIVE")
	protected Boolean isActive=Boolean.TRUE;


}
