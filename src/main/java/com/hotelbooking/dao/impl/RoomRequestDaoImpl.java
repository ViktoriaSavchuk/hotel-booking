package com.hotelbooking.dao.impl;

import com.hotelbooking.dao.RoomRequestDao;
import com.hotelbooking.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class RoomRequestDaoImpl implements RoomRequestDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void update(Room room) {
        entityManager.merge(room);
    }

    @Override
    public Room getRoomByNumber(int roomNumber) {
        return entityManager.find(Room.class, roomNumber);
    }

    @Override
    public Collection<Room> getRoomByDate(Date date) {

        List<Room> freeRooms= new ArrayList<>();

        while (entityManager.find(Room.class, date)!=null) {

            freeRooms.add(entityManager.find(Room.class, date));

        }

        return null;
    }

    @Override
    public List<Room> getAllRooms() {

            CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery=criteriaBuilder.createQuery(Room.class);

            Root<Room> from = criteriaQuery.from(Room.class);

            CriteriaQuery<Room> all =criteriaQuery.select(from);
            TypedQuery<Room> allQuery =entityManager.createQuery(all);

            return allQuery.getResultList();



    }

}

