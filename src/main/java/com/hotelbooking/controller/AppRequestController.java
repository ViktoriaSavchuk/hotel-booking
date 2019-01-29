package com.hotelbooking.controller;

import com.hotelbooking.CustomErrorType;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;
import com.hotelbooking.services.ReservationRequestService;
import com.hotelbooking.services.RoomRequestService;
import com.hotelbooking.services.UserRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class AppRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private RoomRequestService roomRequestService;

    @Autowired
    private ReservationRequestService reservationRequestService;

    private static final Logger logger = LoggerFactory.getLogger(AppRequestController.class);


    //update and show list of actual reservations
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> updateReservations() {

        logger.info("Deleting expired reservations");
        List<Reservation> reservations = reservationRequestService.detAllReservations();
        reservationRequestService.deleteExpiredOrders(reservations);
        reservations = reservationRequestService.detAllReservations();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("userId") int userId) {
        logger.info("Fetching User with id {}", userId);
        User user = userRequestService.getUserByID(userId);
        if (user == null) {
            logger.error("User with id {} not found.", userId);
            return new ResponseEntity<>(new CustomErrorType("User with id " + userId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);

        if (!userRequestService.isUserExist(user)) {
            logger.error("Unable to create. A User with phone {} already exist", user.getPhone());
            return new ResponseEntity(new CustomErrorType("User with phone " + user.getPhone() + " was registrated"),
                    HttpStatus.NOT_FOUND);
        }

        userRequestService.create(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}orders", method = RequestMethod.GET)
    public ResponseEntity<?> getUserReservation(@PathVariable("userId") int userId) {
        logger.info("Fetching  reservations for user {}", userId);
        User user = userRequestService.getUserByID(userId);

        if (user == null || user.getReservations() == null) {
            logger.error("User {} with reservations not found.", userId);
            return new ResponseEntity<>(new CustomErrorType("User {} with reservations not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.getReservations(), HttpStatus.OK);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomByDate(@PathVariable("date") String date) throws ParseException {
        logger.info("Fetching free rooms by date {}", date);
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        List<Room> rooms = roomRequestService.allFreeRoomsForSpecialDate(date1);
        if (rooms == null) {
            logger.error("Free rooms for {} not found.", date);
            return new ResponseEntity<>(new CustomErrorType("Free rooms for " + date
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomByCategory(@PathVariable("category") String category) {
        logger.info("Fetching  rooms by category {}", category);
        List<Room> rooms = roomRequestService.roomsByCategory(category);
        if (rooms == null) {
            logger.error("Rooms with this category( {} ) not found.", category);
            return new ResponseEntity<>(new CustomErrorType("Rooms with this category (" + category
                    + ") not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/", method = RequestMethod.POST)
    public ResponseEntity<?> createReserv(@RequestBody Reservation reservation, UriComponentsBuilder ucBuilder) {
        logger.info("Creating reservation {} on  {}", reservation.getRoomNumber(), reservation.getDate());

        try {
            reservationRequestService.create(reservation);
        } catch (NullPointerException e) {
            logger.error("Unable to create reservation");
            return new ResponseEntity<>(new CustomErrorType("Unable to reserve room " +
                    reservation.getRoomNumber() + " on date " + reservation.getDate()), HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }




}
