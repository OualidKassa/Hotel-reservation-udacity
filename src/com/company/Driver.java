package com.company;

import com.company.model.Customer;
import com.company.model.IRoom;
import com.company.model.Room;
import com.company.model.RoomType;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

public class Driver {

    public static void main(String[] args) {
        Customer customer = new Customer("test","test","test@test.com");
        ReservationService reservationService = ReservationService.getReservationServiceInstance();
        CustomerService customerService = CustomerService.getCustomerServiceInstance();
        customerService.addCustomer(customer.getEmail(), customer.getFirstName(), customer.getLastName());
        IRoom room = new Room("100", 100.0, RoomType.DOUBLE, true);
        reservationService.addRoom(room);
        MainMenu mainMenu = MainMenu.getInstanceMainMenu();
        mainMenu.printMenu();
    }
}
