package org.nithesh.service;

import static org.nithesh.repository.BookingDataRepository.getBookingDataRepository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import org.nithesh.exception.BookingNotFound;
import org.nithesh.mode.ConsoleLog;
import org.nithesh.mode.Logger;
import org.nithesh.model.Booking;
import org.nithesh.model.TimeSlot;
import org.nithesh.repository.BookingDataRepository;
import org.nithesh.repository.LocationDataRepository;

public class BookingService {

  Logger logger;
  public BookingService(){
    logger = new ConsoleLog();
  }

  public void bookRoom(TimeSlot timeSlot, String roomId, String  floorId, String buildingId){
    LocationDataRepository locationDataRepository = LocationDataRepository.getLocationDataRepository();
    if(!locationDataRepository.checkRoomExists(roomId)){
      logger.logData("Room does not exists");
      return;
    }
    if(!locationDataRepository.checkFloorExists(floorId)){
      logger.logData("Floor does not exists");
      return;
    }
    if(!locationDataRepository.checkBuildingExists(buildingId)){
      logger.logData("Building does not exists");
      return;
    }
    Integer id =  getBookingDataRepository().bookRoom(timeSlot,roomId);
    logger.logData("Booked room id :"+id );
  }

  public void cancelBooking(Integer id){
    try {
      getBookingDataRepository().cancelBooking(id);
    } catch (BookingNotFound e) {
      logger.logData("booking not found");
      return;
    }
    logger.logData("Booked canceled");
  }

  public void getAllBooking(){
    Collection<Booking> bookings =  BookingDataRepository.getBookingDataRepository().getAllBookings();
    for(Booking booking : bookings){
      logger.logData(booking.getId() + " " + booking.getConferenceId() + " " + booking.getTimeSlot().getStartTime() + "-" +  booking.getTimeSlot().getEndTime());
    }
    if(bookings.isEmpty())
      logger.logData("no bookings");
    else
      logger.logData("-----------------");;
  }

  public void getAvailableTimeSlots(){
    HashMap<String, ArrayList<TimeSlot>> availability = BookingDataRepository.getBookingDataRepository()
        .getAvailableTimeSlots();
    for(Entry<String, ArrayList<TimeSlot>> entry:availability.entrySet()){
      logger.logData(entry.getKey() +":" );
      for(TimeSlot timeSlot : entry.getValue()){
        logger.logData(timeSlot.getStartTime()+" - " + timeSlot.getEndTime());
      }
    }
    logger.logData("-------------------------");
  }

}
