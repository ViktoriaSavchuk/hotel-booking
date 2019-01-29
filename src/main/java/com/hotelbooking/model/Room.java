package com.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private float price;


    @OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    //@JsonBackReference
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Reservation> reservations;


    public Room() {
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }


    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}






