package com.hotelbooking.services.impl;

import com.hotelbooking.dao.ReservationRequestDao;
import com.hotelbooking.dao.RoomRequestDao;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.services.RoomRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoomRequestServiceImpl implements RoomRequestService {

    @Autowired
    private RoomRequestDao roomRequestDao;

    @Autowired
    private ReservationRequestDao reservationRequestDao;

    @Override
    public List<Room> allFreeRoomsForSpecialDate(Date date) {

        List<Reservation> allReservations=reservationRequestDao.getAllReservations();
        List<Room> allRooms = roomRequestDao.getAllRooms();
        List<Room> freeRooms=new ArrayList<>();

        for(Reservation reservation: allReservations){

            if(!reservation.getDate().equals(date)) {

                allRooms.remove(getRoom(reservation.getRoomNumber()));
            }
        }

        return allRooms;
    }

    @Override
    public List<Room> roomsByCategory(String category) {

        List<Room> allRooms = roomRequestDao.getAllRooms();
        List<Room> categoryRoom = new ArrayList<>();
        for(Room room: allRooms ){

            if(room.getCategory().equals(category)) categoryRoom.add(room);

        }
        return categoryRoom;

    }

    @Override
    public Room getRoom(int roomNumber) {
        return roomRequestDao.getRoomByNumber(roomNumber);
    }
}
