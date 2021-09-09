package com.company.api;

import com.company.model.Customer;
import com.company.model.IRoom;
import com.company.model.Reservation;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource = null;
    ReservationService reservationService = ReservationService.getReservationServiceInstance();
    CustomerService customerService = CustomerService.getCustomerServiceInstance();

    private HotelResource() { }

    public static HotelResource getInstanceHotelResource(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }
    public Customer getCustomer(String email){
        CustomerService customerService = CustomerService.getCustomerServiceInstance();
        return customerService.getCustomer(email);
    }
    public  void createACustomer(String email, String firstName, String lastName){

        customerService.addCustomer(email, firstName, lastName);
    }
    public IRoom getRoom(String roomNumber){
        return reservationService.getARomm(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customerBook = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customerBook, room, checkInDate, checkOutDate );
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customerAllBook = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customerAllBook);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }
}
