package com.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private int orderId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "room_number")
    private int roomNumber;

    // @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "reserve_date")
    //@CreationTimestamp
    private Date date;

    @Column(name = "breakfast")
    private boolean breakfast;

    @Column(name = "cleaning")
    private boolean cleaning;

    @Column(name = "order_price")
    private float orderPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    //@JsonBackReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User users;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "room_number", insertable = false, updatable = false)
    // @JsonBackReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Room rooms;

    public Reservation() {
    }



    public Reservation(Integer userId, int roomNumber, Date date, boolean breakfast, boolean cleaning, float orderPrice) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.date = date;
        this.breakfast = breakfast;
        this.cleaning = cleaning;
        this.orderPrice = orderPrice;

    }

    public Reservation(int userId, int roomNumber, Date date, boolean breakfast, boolean cleaning) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.date = date;
        this.breakfast = breakfast;
        this.cleaning = cleaning;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean getCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Room getRooms() {
        return rooms;
    }

    public void setRooms(Room rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", roomNumber=" + roomNumber +
                ", date=" + date +
                ", breakfast=" + breakfast +
                ", cleaning=" + cleaning +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
