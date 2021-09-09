package com.company.service;

import com.company.model.Customer;
import com.company.model.IRoom;
import com.company.model.Reservation;

import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;

    private Set<Reservation> reservationList = new HashSet<>();
    private Set<IRoom> roomSet = new HashSet<>();
    private Set<IRoom> roomNotAvailable = new HashSet<>();

    private ReservationService() {}

    public static ReservationService getReservationServiceInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        roomSet.add(room);
    }

    public void addRoomNotAvailable(IRoom room){
        roomNotAvailable.add(room);
    }
    
    public IRoom getARomm(String roomId){
        for(IRoom room : roomSet){
            if (room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        room.setIsFree(false);
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> findsRoom = new ArrayList<>();
        if(reservationList.isEmpty()){
            findsRoom.addAll(roomSet);
        }else {
            for (Reservation res : reservationList) {
                if (!res.getDateChekIn().equals(checkInDate) && !res.getDateCheckOut().equals(checkOutDate)) {
                    findsRoom.add(res.getRoom());
                }
            }
        }
          return findsRoom;
    }


    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customerReservations = new ArrayList<>();
        reservationList.forEach(cusRes -> {
            if (cusRes.getCustomer().equals(customer)){
                customerReservations.add(cusRes);
            }
        });
        return customerReservations;
    }
    public void printAllReservation(){
        if(reservationList.isEmpty()){
            System.out.println("There is no reservation!");
        }
        reservationList.forEach(System.out::println);
    }
    public  Collection<IRoom> printAllRooms() {
        return roomSet;
    }

    public Set<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(Set<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
