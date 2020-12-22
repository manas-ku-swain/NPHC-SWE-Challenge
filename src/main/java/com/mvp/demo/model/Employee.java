/**
 * 
 */
package com.mvp.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
	@JsonFormat(pattern="yyyy/MM/dd")
	//@JsonFormat(pattern="dd-mmm-yy")
	@JsonProperty("start_date")
	@Id
	private String id;
	private String login;	
	private String name;
	private double salary;
	private String startDate;
	
	
	

}
