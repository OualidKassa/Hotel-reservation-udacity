package com.company;

import com.company.model.*;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu adminMenu = null;
    ReservationService reservationService = ReservationService.getReservationServiceInstance();
    CustomerService customerService = CustomerService.getCustomerServiceInstance();
    private AdminMenu(){}
    public static AdminMenu getAdminMenuInstance(){
        if (adminMenu == null){
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void printMenu(){
        System.out.println("1. See all customers\n" +
                "2. See all rooms\n" +
                "3. See all reservation\n" +
                "4. Add a room\n" +
                "5. Back to main menu");
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
                case 5:
                    MainMenu mainMenu = MainMenu.getInstanceMainMenu();
                    mainMenu.printMenu();
                    break;

                case 4:
                    IRoom iroom = getRoomInformation();
                    reservationService.addRoom(iroom);
                    reservationService.printAllRooms();
                    printMenu();
                    break;

                case 1:
                    System.out.println(customerService.getAllCustomers());
                    printMenu();
                    break;
                case 3:
                    reservationService.printAllReservation();
                    printMenu();
                    break;
                case 2:
                    System.out.println(reservationService.printAllRooms());
                    printMenu();
                    break;

            }
        } catch (InputMismatchException | InvalidRoomSetUpException ex){
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Please enter only number between 1 and 5!!!");
            printMenu();
        }
    }

    private IRoom getRoomInformation() throws InvalidRoomSetUpException {
        String roomNumber;
        double price;
        RoomType roomType = RoomType.SINGLE;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the room number");
        roomNumber = scanner.nextLine();
        System.out.println("Please enter the price");
        String priceText = scanner.nextLine();
        try {
            price = Double.parseDouble(priceText);
        } catch(Exception ex) {
            throw new InvalidRoomSetUpException("Room price needs to be a number greater than 0");
        }
        if (price <= 0.0) {
            throw new InvalidRoomSetUpException("Room price needs to be a number greater than 0");
        }
        System.out.println("Please enter the room type (single/double)");
        String roomTypeString = scanner.nextLine();
        if (roomTypeString.equals("single") || roomTypeString.equals("double")) {
            if (roomTypeString.equals("double")) {
                roomType = RoomType.DOUBLE;
            }
        } else {
            throw new InvalidRoomSetUpException("Room type needs to be either single our double.");
        }
        IRoom room = new FreeRoom(roomNumber, price, roomType, true);
        reservationService.addRoom(room);
        return room;
    }
}
