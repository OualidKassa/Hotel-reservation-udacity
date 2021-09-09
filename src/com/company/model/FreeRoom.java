package com.company.model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType enumRoomType,boolean isfree) {
        super(roomNumber, price ,enumRoomType, isfree);
    }

    @Override
    public String toString() {
        return "FreeRoom: "+ getRoomNumber()+" "+getRoomPrice()+" "+getRoomType();
    }
}
