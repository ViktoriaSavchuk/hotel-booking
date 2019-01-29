package com.hotelbooking.dao;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationRequestDao {


    void create(Reservation reservation);


    Reservation getReservationById(int reserve_id);

    List<Reservation> getAllReservations();

    void delete(int id);

    boolean checkReservationByDateAndRoom(int orderId, Date date);

}
