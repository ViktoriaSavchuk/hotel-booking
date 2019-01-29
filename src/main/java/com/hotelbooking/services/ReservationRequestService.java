package com.hotelbooking.services;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;

import java.util.Date;
import java.util.List;

public interface ReservationRequestService {

    void create(Reservation reservation);

    void deleteExpiredOrders(List<Reservation> reservations);

    List<Reservation> detAllReservations();
}
