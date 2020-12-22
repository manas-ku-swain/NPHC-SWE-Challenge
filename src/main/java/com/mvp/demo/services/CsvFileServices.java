/**
 * 
 */
package com.mvp.demo.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvp.demo.NphcApplication;
import com.mvp.demo.model.Employee;
import com.mvp.demo.repo.EmployeeRepository;
import com.mvp.demo.repo.EmployeeRepository2;
import com.mvp.demo.utils.ApacheCommonsCsvUtil;
import com.mvp.demo.utils.CsvUtil;
import com.opencsv.bean.OpencsvUtils;

/**
 * @author User
 *
 */
@Service
public class CsvFileServices {
	Logger LOG = LoggerFactory.getLogger(CsvFileServices.class);
	@Autowired
	EmployeeRepository2 employeeRepository;
	// Store Csv File's data to database
	public void persist(InputStream file) {
		
		try {
			//using opencsv utills to pasre CSV file
			List<Employee> lstEmployees = CsvUtil.parseCsvFile(file);
			
			employeeRepository.saveAll(lstEmployees);
		}catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	// Load Data to CSV File
    public void loadFile(Writer writer) throws IOException {
    	try {
        	List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        	
        	// Using ApacheCommons Csv Utils to write Customer List objects to a Writer
            ApacheCommonsCsvUtil.customersToCsv(writer, employees);
        	
        	// Using Open CSV Utils to write Customer List objects to a Writer
        	 CsvUtil.employeeToCsv(writer, employees);    		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
    
    public List<Employee> getEmployeeList(){
    
		return  employeeRepository.findAll();
 	
    }
    
    public Employee getEmployeeById(String id){
        
		return  employeeRepository.findById(id).orElse(null); 	
    }
    public Optional<Employee> getEmployeeByMaxSalary(double salary){
        
    	
    	List<Employee> employeeList =  employeeRepository.findAll();
        
    	Optional<Employee> maxSalaryEmp = 
                employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
      
        Optional<Employee> minSalaryEmp =    
                employeeList.stream()
                .collect(Collectors.minBy(Comparator.comparing(Employee::getSalary)));
        
  		return  maxSalaryEmp;
      }
    
 public Employee saveEmployee(Employee employee){
    	
    	return employeeRepository.save(employee);
    }
    
    public List<Employee> saveEmployees(List<Employee> employees){
    	
    	return employeeRepository.saveAll(employees);
    }
    
    public String deleteEmplyoee(String id) {
    	  employeeRepository.deleteById(id);	
    	
    	return "Employee has been removed !! "+id;
    }
    public Employee updateEmployee(Employee employee) {
    	
    	Employee existingEmployee=employeeRepository.findById(employee.getId()).orElse(null);
    	existingEmployee.setName(employee.getName());
    	existingEmployee.setId(employee.getId());
    	existingEmployee.setSalary(employee.getSalary());
    	existingEmployee.setStartDate(employee.getStartDate());
    	existingEmployee.setLogin(employee.getLogin());
    	
    	return employeeRepository.save(existingEmployee);
    	
    	
    }
    
    
    
    
}
