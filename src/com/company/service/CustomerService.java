package com.company.service;

import com.company.model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomerService {

    private static CustomerService customerService = null;
    private List<Customer> customers = new ArrayList<>();

    private CustomerService() {
    }

    public static CustomerService getCustomerServiceInstance(){
        if (customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }



    public void addCustomer(String email, String firstName, String lastName){
        try {
            Customer customer = new Customer(firstName, lastName, email);
            customers.add(customer);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage()+" please insert valid email!");
        }
    }
    public Customer getCustomer(String customerEmail){
        for (Customer customer : customers){
            if(customer.getEmail().equalsIgnoreCase(customerEmail)){
                return customer;
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers(){return customers;}
}
