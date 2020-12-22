/**
 * 
 */
package com.mvp.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;


/**
 * @author User
 *
 */
@Entity

@Table(name = "employee")
public class Employee2 {
	public Employee2() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	private String id;
	private String login;	
	private String name;
	private String salary;
	private String startDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Employee2(String id, String login, String name, String salary, String startDate) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", login=" + login + ", name=" + name + ", salary=" + salary + ", startDate="
				+ startDate + "]";
	}
	
	
	

}
