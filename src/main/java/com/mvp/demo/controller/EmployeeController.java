package com.mvp.demo.controller;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvp.demo.error.RecordNotFoundException;
import com.mvp.demo.model.EmployeeEntity;
import com.mvp.demo.services.EmployeeService;
 

 
@Controller
@RequestMapping("/")
public class EmployeeController 
{
	
	@PersistenceContext
    EntityManager entityManager;
    @Autowired
    EmployeeService service;
 
    @RequestMapping
    public String getAllEmployees(Model model) 
    {
        List<EmployeeEntity> list = service.getAllEmployees();
 
        model.addAttribute("employees", list);
        return "EmployeeList";
    }
 
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<String> id) 
                            throws RecordNotFoundException 
    {
        if (id.isPresent()) {
            EmployeeEntity entity = service.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        } else {
            model.addAttribute("employee", new EmployeeEntity());
        }
        return "add-edit-employee";
    }
     
    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") String id) 
                            throws RecordNotFoundException 
    {
        service.deleteEmployeeById(id);
        return "redirect:/";
    }
 
    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(EmployeeEntity employee) 
    {
        service.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}