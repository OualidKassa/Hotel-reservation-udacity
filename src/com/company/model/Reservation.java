package com.company.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date dateChekIn;
    private Date dateCheckOut;

    public Reservation(Customer customer, IRoom room, Date dateChekIn, Date dateCheckOut) {
        this.customer = customer;
        this.room = room;
        this.dateChekIn = dateChekIn;
        this.dateCheckOut = dateCheckOut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getDateChekIn() {
        return dateChekIn;
    }

    public void setDateChekIn(Date dateChekIn) {
        this.dateChekIn = dateChekIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", dateChekIn=" + dateChekIn +
                ", dateCheckOut=" + dateCheckOut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getCustomer().equals(that.getCustomer()) &&
                getRoom().equals(that.getRoom()) &&
                getDateChekIn().equals(that.getDateChekIn()) &&
                getDateCheckOut().equals(that.getDateCheckOut());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getRoom(), getDateChekIn(), getDateCheckOut());
    }
}
