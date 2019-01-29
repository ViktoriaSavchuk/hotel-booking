package com.hotelbooking.dao;

import com.hotelbooking.model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RoomRequestDao {

    void update(Room room);

    Room getRoomByNumber(int roomNumber);
    Collection<Room> getRoomByDate(Date date);

    List<Room> getAllRooms();



}
