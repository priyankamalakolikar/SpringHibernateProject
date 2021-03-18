package com.love2code.springdemo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.love2code.springdemo.dao.CustomerDAO;
import com.love2code.springdemo.entity.Customer;
import com.love2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	/*
	 * //inject the customer dao
	 * 
	 * @Autowired //spring scan 4 component that implelents customerDAO interface
	 * private CustomerDAO customerDAO;
	 */
	
	//need to inject our customer Service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model themodel) {
		//get customer from the service
		List<Customer>  thecustomer=customerService.getCustomers(); //delegate call to Service
		
		//add customers to model
		themodel.addAttribute("customers",thecustomer); //name value pair
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model themodel) {
		
		//create model attribute to form data
		Customer theCustomer=new Customer();
		themodel.addAttribute("customer", theCustomer);
		
		
		return "customer-form";
	}
	
	//this action for saving customer data 
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//save customer to using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	//this action for updating customer data 
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int theId,Model theModel) {
		
		// get the customer from our service
				Customer theCustomer = customerService.getCustomer(theId);	
				
				// set customer as a model attribute to pre-populate the form
				 //here name value parir and map using customer-form.jsp
				//file  <form:form  action="saveCustomer" modelAttribute="customer" method="POST">
				theModel.addAttribute("customer", theCustomer);
				// send over to our form		
				return "customer-form";
	}
	
	//delete customer code
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		//above customerID parame nae should map with list-customer.jsp param in delete
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
