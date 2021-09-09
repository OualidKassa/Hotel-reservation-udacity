package com.company.model;

import java.util.Objects;

public class Room implements IRoom {


    private String roomNumber;
    private Double price;
    private RoomType enumRoomType;
    private boolean isFree;

    public Room(String roomNumber, Double price, RoomType enumRoomType, boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumRoomType = enumRoomType;
        this.isFree = isFree;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumRoomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public void setIsFree(boolean isfree) {
        this.isFree = isfree;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumRoomType=" + enumRoomType +
                ", isFree=" + isFree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getRoomNumber().equals(room.getRoomNumber()) &&
                price.equals(room.price) &&
                enumRoomType == room.enumRoomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), price, enumRoomType);
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomType getEnumRoomType() {
        return enumRoomType;
    }

    public void setEnumRoomType(RoomType enumRoomType) {
        this.enumRoomType = enumRoomType;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
