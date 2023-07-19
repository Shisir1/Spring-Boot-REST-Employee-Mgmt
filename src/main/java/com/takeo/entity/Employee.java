package com.takeo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//column name should be in this format otherwise it throws an error
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", updatable = false)
	private Date createDate;
	
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", insertable = false)
	private Date updateDate;
	
	

}
