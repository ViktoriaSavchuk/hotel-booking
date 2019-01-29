package com.hotelbooking.services;

import com.hotelbooking.model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RoomRequestService {

    List<Room> allFreeRoomsForSpecialDate(Date date);
    List<Room> roomsByCategory(String category);

    Room getRoom(int roomNumber);

   // Collection<Room>

}
