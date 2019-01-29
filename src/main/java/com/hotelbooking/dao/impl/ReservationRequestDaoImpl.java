package com.hotelbooking.dao.impl;

import com.hotelbooking.dao.ReservationRequestDao;
import com.hotelbooking.dao.RoomRequestDao;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

//import org.hibernate.criterion.CriteriaQuery;

@Repository
public class ReservationRequestDaoImpl implements ReservationRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RoomRequestDao roomRequestDao;


    @Override
    public void create(Reservation reservation) {
        reservation.setOrderPrice(0);
        if (checkReservationByDateAndRoom(reservation.getRoomNumber(), reservation.getDate())) {
            reservation.setOrderPrice(roomRequestDao.getRoomByNumber(reservation.getRoomNumber()).getPrice());

            if (reservation.getBreakfast()) {
                reservation.setOrderPrice((float) (reservation.getOrderPrice()+ 70.99));
                if (reservation.getCleaning()) {
                    reservation.setOrderPrice((float) (reservation.getOrderPrice()+ 85.99));
                }
                System.out.println(reservation);
                entityManager.persist(reservation);
            } else throw new NullPointerException("can not create reservation");

        }
    }


    @Override
    public Reservation getReservationById(int reserve_id) {
        return entityManager.find(Reservation.class, reserve_id);
    }

    @Override
    public boolean checkReservationByDateAndRoom(int roomNumber, Date date) {

        List<Reservation> reservations = getAllReservations();

        for (Reservation reservation : reservations) {
            if (date.equals(reservation.getDate()) && reservation.getRoomNumber() == roomNumber) {
                return false;
            }
        }
        return true;
    }



    @Override
    public List<Reservation> getAllReservations() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);

        Root<Reservation> from = criteriaQuery.from(Reservation.class);

        CriteriaQuery<Reservation> all = criteriaQuery.select(from);
        TypedQuery<Reservation> allQuery = entityManager.createQuery(all);

        return allQuery.getResultList();


    }


    @Override
    public void delete(int reserve_id) {

        Reservation reservation = getReservationById(reserve_id);
        System.out.println(reservation);
        if (reservation != null) {
            entityManager.remove(entityManager.merge(reservation));
        }

    }
}
