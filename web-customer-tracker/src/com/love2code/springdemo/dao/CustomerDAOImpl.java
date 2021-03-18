package com.love2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.love2code.springdemo.entity.Customer;

@Repository //this require for component scan,find reposotry and handle exception transalation for us
public class CustomerDAOImpl implements CustomerDAO {

	
	//need to inject session factory of hibernate
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		
		//create hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//create query 
		Query<Customer> thequery=currentSession.createQuery("from Customer order by lastName",Customer.class);
		//execute query and get list
		List<Customer> customers=thequery.getResultList();
		
		//return result
		return customers;
	}

//code save customer using hibernate 
	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//save /Update the customer
		/* currentSession.save(theCustomer); */
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomers(int theId) {
		// TODO Auto-generated method stub
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//retrieve read data from database using primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				// delete object with primary key
				Query theQuery = 
						currentSession.createQuery("delete from Customer where id=:customerId");//here HQL we used
				theQuery.setParameter("customerId", theId);
				
				theQuery.executeUpdate();
	}

}
