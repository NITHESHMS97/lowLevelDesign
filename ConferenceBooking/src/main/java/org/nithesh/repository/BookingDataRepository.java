package org.nithesh.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import org.nithesh.exception.BookingNotFound;
import org.nithesh.exception.ConflictInBooking;
import org.nithesh.model.Booking;
import org.nithesh.model.TimeSlot;

public class BookingDataRepository {

  HashMap<String, ArrayList<Integer>> conferenceBookingData;
  HashMap<Integer, Booking> bookingData;
  static BookingDataRepository bookingDataRepository = null;

  Integer bookingIdGenerator;

  private BookingDataRepository() {
    conferenceBookingData = new HashMap<String, ArrayList<Integer>>();
    bookingData = new HashMap<Integer, Booking>();
    bookingIdGenerator = 0;
  }

  public Integer getBookingId() {
    bookingIdGenerator += 1;
    return bookingIdGenerator;
  }

  public static synchronized BookingDataRepository getBookingDataRepository() {
    if (bookingDataRepository == null) {
      bookingDataRepository = new BookingDataRepository();
    }
    return bookingDataRepository;
  }

  public Collection<Booking> getAllBookings() {
    return bookingData.values();
  }

  public void addAvailableSlotsForRoom(String roomId){
    conferenceBookingData.computeIfAbsent(roomId, k->new ArrayList<>());
  }
  public Integer bookRoom(TimeSlot timeSlot, String roomId) {

    boolean conflictPresent = bookingData.values().stream().anyMatch(
        booking -> timeSlot.getStartTime() >= booking.getTimeSlot().getStartTime()
            && timeSlot.getStartTime() < booking.getTimeSlot().getEndTime());
    if (conflictPresent) {
      throw new ConflictInBooking("conflict present");
    }

    Integer id = getBookingId();
    Booking booking = new Booking(id, roomId, timeSlot);
    bookingData.put(id, booking);
    conferenceBookingData.computeIfAbsent(roomId, k -> new ArrayList<>()).add(id);
    return id;

  }

  public void cancelBooking(Integer bookingId) throws BookingNotFound {
    if(!bookingData.containsKey(bookingId))
      throw new BookingNotFound();
    String roomId = bookingData.get(bookingId).getConferenceId();
    conferenceBookingData.get(roomId).remove(bookingId);
    bookingData.remove(bookingId);
  }
  public HashMap<String, ArrayList<TimeSlot>> getAvailableTimeSlots(){
    HashMap<String, ArrayList<TimeSlot>> availableSlots = new HashMap<>();
    for( Entry<String, ArrayList<Integer>> entry :conferenceBookingData.entrySet()){
      String conferenceId = entry.getKey();
      availableSlots.put(conferenceId, new ArrayList<>());
      if(entry.getValue().isEmpty())
        availableSlots.get(conferenceId).add(new TimeSlot(1F,24F));
      else{
        Float sTime=1F,eTime;
        for(Integer bookingId :entry.getValue()){
          eTime = bookingData.get(bookingId).getTimeSlot().getStartTime();
          if(!Objects.equals(sTime, eTime))
            availableSlots.get(conferenceId).add(new TimeSlot(sTime,eTime));
          sTime=bookingData.get(bookingId).getTimeSlot().getEndTime();
        }
        if(sTime!=24F)
          availableSlots.get(conferenceId).add(new TimeSlot(sTime,24F));
      }

    }
    return availableSlots;
  }
}
