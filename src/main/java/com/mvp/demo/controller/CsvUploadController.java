
package com.mvp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mvp.demo.NphcApplication;
import com.mvp.demo.message.Message;
import com.mvp.demo.message.Response;
import com.mvp.demo.model.Employee;
import com.mvp.demo.repo.EmployeeRepository;
import com.mvp.demo.services.CsvFileServices;
import com.mvp.demo.utils.ApacheCommonsCsvUtil;

/**
 * @author User
 *
 */
@CrossOrigin
@RestController
//@RequestMapping( "/")
public class CsvUploadController {

	Logger LOG = LoggerFactory.getLogger(CsvUploadController.class);
	@Autowired
	CsvFileServices csvFileServices;
	
	@PostMapping(path= "/users/upload")
	public Response uploadSingleCSVFile(@RequestParam("file") MultipartFile file) {
	//LOG.info("uploadSingleCSVFile");
		Response response = new Response();
	
		// Checking the upload-file's name before processing
		if (file.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(file.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));
	
			return response;
		}
	
		// checking the upload file's type is CSV or NOT
		
	/**	if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail")); 
	        return response; 
		}**/
		  
		 
		try {
			// save file data to database
			csvFileServices.persist(file.getInputStream());
			response.addMessage(new Message(file.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(file.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}



	@PostMapping("/users/addusers")
	public List<Employee> addEmployee(@RequestBody List<Employee> employees) {
		
			
		return csvFileServices.saveEmployees(employees);

	}
	@PostMapping("/user/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		
			
		return csvFileServices.saveEmployee(employee);

	}
	@GetMapping("/employees")
	public  String findAllEmployees(Model model){
		List<Employee> emp= csvFileServices.getEmployeeList();
		model.addAttribute("emplist",emp);
		
		return "DetailsInfoPage";
		
		
		
	}
	@GetMapping("/employee/{id}")
	public Employee findEmployeeById(@PathVariable String id){
		return csvFileServices.getEmployeeById(id);
	}

	@GetMapping("/employee/{salary}")
	public Optional<Employee> findEmployeeByMaxSalary(@PathVariable Double salary){
		return csvFileServices.getEmployeeByMaxSalary(salary);
	}
	
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
			
		return csvFileServices.updateEmployee(employee);

	}
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(String id) {
		
		Response response = new Response();
		try {
		return csvFileServices.deleteEmplyoee(id);
		
		//response.addMessage(new Message(null, "","fail"));
	} catch (Exception e) {
		response.addMessage(new Message(csvFileServices.deleteEmplyoee(id), e.getMessage(), "fail"));
	}
		return csvFileServices.deleteEmplyoee(id);
	}
	
	
	
	
}
