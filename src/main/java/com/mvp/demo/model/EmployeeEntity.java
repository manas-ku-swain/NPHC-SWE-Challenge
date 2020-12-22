/**
 * 
 */
package com.mvp.demo.model;

/**
 * @author User
 *
 */import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
  
 @Entity
 @Table(name="EMPLOYEE")
 public class EmployeeEntity {
  
     @Id
  //   @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String id;
	private String login;	
 	private String name;
 	private double salary;
 	private String startDate;
      
     //Setters and getters
 	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", login=" + login + ", name=" + name + ", salary=" + salary
				+ ", startDate=" + startDate + "]";}

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
     
 }