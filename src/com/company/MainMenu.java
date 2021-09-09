package com.company;

import com.company.model.*;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private static MainMenu mainMenu = null;
    CustomerService customerService = CustomerService.getCustomerServiceInstance();
    ReservationService reservationService = ReservationService.getReservationServiceInstance();
    AdminMenu adminMenu = AdminMenu.getAdminMenuInstance();
    public static MainMenu getInstanceMainMenu(){
        if(mainMenu == null){
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }
    private MainMenu(){}

    public void printMenu(){
        System.out.println("1. Find and reserve a room\n" +
                "2. See my reservation\n" +
                "3. Create a account\n" +
                "4. Admin\n" +
                "5. Exit");
        selectMenu();
    }

    public void selectMenu(){
        try {
            Scanner selectedMenu = new Scanner(System.in);
            int choose = selectedMenu.nextInt();
            if (choose < 1 || choose > 5) {
                System.out.println("Please enter only number between 1 and 5!!!");
                printMenu();
            }

            switch (choose){
                case 3:

                    System.out.println("Enter the customer first name:");
                    Scanner firstNameScanner = new Scanner(System.in);
                    String firstName = firstNameScanner.next();

                    System.out.println("Enter the customer last name:");
                    Scanner lastNameScanner = new Scanner(System.in);
                    String lastName = lastNameScanner.next();

                    System.out.println("Enter the customer email:");
                    Scanner emailScanner = new Scanner(System.in);
                    String emailName = emailScanner.next();

                    customerService.addCustomer(emailName, firstName, lastName);
                    printMenu();
                    break;

                case 4:

                    adminMenu.printMenu();
                    break;
                case 5:
                    System.exit(0);
                case 2:
                    System.out.println("Enter the customer email:");
                    Scanner emailReservationScanner = new Scanner(System.in);
                    String emailReservation = emailReservationScanner.next();
                    if (reservationService.getCustomersReservation(customerService.getCustomer(emailReservation)).isEmpty()){
                        System.out.println("no reservation with this email!");
                    }else{
                        System.out.println(reservationService.getCustomersReservation(customerService.getCustomer(emailReservation)));
                    }
                    break;
                case 1:
                    System.out.println("Please insert your check in date:");
                    Scanner dateChekInScanner = new Scanner(System.in);
                    Date dateCheckIn = new SimpleDateFormat("MM/dd/yyyy").parse(dateChekInScanner.next());

                    System.out.println("Please insert your check out date");
                    Scanner dateChekOutScanner = new Scanner(System.in);
                    Date dateCheckOut = new SimpleDateFormat("MM/dd/yyyy").parse(dateChekOutScanner.next());
                    Collection<IRoom> roomsAvailables =  reservationService.findRooms(dateCheckIn, dateCheckOut);

                    if(roomsAvailables.isEmpty()){
                        System.out.println(roomsAvailables);
                        Calendar c = Calendar.getInstance();
                        c.setTime(dateCheckIn);
                        c.add(Calendar.DAY_OF_WEEK, 7);
                        dateCheckIn = c.getTime();

                        Calendar c2 = Calendar.getInstance();

                        c2.setTime(dateCheckOut);
                        c2.add(Calendar.DAY_OF_WEEK, 7);
                        dateCheckOut = c2.getTime();
                        System.out.println("Sorry there is no available room for these dates, we will search if there is available room for the next week. "+dateCheckIn+" "+dateCheckOut);
                        Collection<IRoom> roomsAvailables2 =  reservationService.findRooms(dateCheckIn, dateCheckOut);
                        if(roomsAvailables2.isEmpty()){
                            System.out.println(roomsAvailables);
                            throw new InvalidRoomSetUpException("Sorry no dates available");
                        }else {
                            System.out.println("We found rooms with these dates: "+dateCheckIn+" "+dateCheckOut);
                            System.out.println("Would you make a reservation Y(yes)/N(no) ?");
                            Scanner response = new Scanner(System.in);
                            String responseReservation;

                            System.out.println("Please type only Y(yes)/N(no)  ?");
                            responseReservation = response.next();

                            if(responseReservation.equalsIgnoreCase("y")){
                                makeReservation(dateCheckIn, dateCheckOut);
                            }else {
                                break;
                            }
                        }
                    }else {
                        makeReservation(dateCheckIn, dateCheckOut);
                    }

            }
        } catch (InputMismatchException | ParseException | CustomerException | InvalidRoomSetUpException ex){
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Please enter only number between 1 and 5!!!");
            printMenu();
        }
        printMenu();
    }

    public void makeReservation(Date dateCheckIn, Date dateCheckOut) throws CustomerException, InvalidRoomSetUpException {
        System.out.println("Please insert your email.");
        Scanner emailScannerForReservation = new Scanner(System.in);
        String emailEnter = emailScannerForReservation.next();
        while(emailEnter.isEmpty()){
            System.out.println("Please insert valid email.");
            emailScannerForReservation.next();
        }

        Customer customer = customerService.getCustomer(emailEnter);
        if(customer == null){
            throw new CustomerException("Sorry you have to register first!");
        }
        Collection<IRoom> rooms = reservationService.findRooms(dateCheckIn, dateCheckOut);
        if(rooms == null){
            throw new InvalidRoomSetUpException("First you have to registre a room!");
        }
        for(IRoom room : reservationService.printAllRooms()){
                System.out.println("room n° " + room.getRoomNumber() + " type room: " + room.getRoomType() + " price: " + room.getRoomPrice() + " is available");
        }
        System.out.println("Please enter the room number of your choice:");
        Scanner roomNumberEnter = new Scanner(System.in);
        String roomNumber = roomNumberEnter.next();

        IRoom roomReserve =  reservationService.getARomm(roomNumber);
        if (roomReserve != null){
            Reservation reservationCustomer = reservationService.reserveARoom(customer, roomReserve,  dateCheckIn, dateCheckOut);
            System.out.println("your reservation resumed: "+reservationCustomer.toString());
        } else {
            throw new InvalidRoomSetUpException("Room number doesn't exist!");
        }

    }
}
