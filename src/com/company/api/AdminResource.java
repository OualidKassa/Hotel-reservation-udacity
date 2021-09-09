package com.company.api;

import com.company.model.Customer;
import com.company.model.CustomerException;
import com.company.model.IRoom;
import com.company.model.Reservation;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource = null;
    CustomerService customerService = CustomerService.getCustomerServiceInstance();
    ReservationService reservationService = ReservationService.getReservationServiceInstance();
    private AdminResource(){}
    public static AdminResource getAdminResourceInstance(){
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email) throws CustomerException {


        Customer customer = customerService.getCustomer(email);
        if(customer == null)
        {
            throw new CustomerException("There is no customer with this email");
        }
        return customer;
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room: rooms) {
            reservationService.addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        Collection<IRoom> rooms = new ArrayList<>();
        reservationService.getReservationList().forEach(getRooms -> rooms.add(getRooms.getRoom()));
        return rooms;
    }
    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    public void displayAllReservations(){
        for (Reservation reservation : reservationService.getReservationList() ){
            System.out.println(reservation);
        }
    }
}
