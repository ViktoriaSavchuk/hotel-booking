package com.hotelbooking.services.impl;

import com.hotelbooking.dao.ReservationRequestDao;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.services.ReservationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ReservationRequestServiceImpl implements ReservationRequestService {

    @Autowired
    private ReservationRequestDao reservationRequestDao;


    @Override
    public void create(Reservation reservation) {

        Calendar cal = Calendar.getInstance();
        Date dateNow = cal.getTime();

        if (reservation.getDate().after(dateNow))reservationRequestDao.create(reservation);
        else throw new NullPointerException("expired date");
    }

    @Override
    public void deleteExpiredOrders(List<Reservation> reservations) {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        for (Reservation reservation : reservations) {
            if (reservation.getDate().before(date)) reservationRequestDao.delete(reservation.getOrderId());
        }

    }

    @Override
    public List<Reservation> detAllReservations() {
        return reservationRequestDao.getAllReservations();
    }
}
