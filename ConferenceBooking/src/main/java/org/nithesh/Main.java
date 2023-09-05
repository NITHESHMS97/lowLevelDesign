package org.nithesh;

import org.nithesh.model.TimeSlot;
import org.nithesh.service.BookingService;
import org.nithesh.service.LocationService;

public class Main {

  public static void main(String[] args) {
    BookingService bookingService = new BookingService();
    LocationService locationService = new LocationService();
    locationService.addBuilding("b1");
    locationService.addBuilding("b2");
    locationService.addFloor("b1","1");
    locationService.addFloor("b1","2");
    locationService.addFloor("b1","3");
    locationService.addRoom("b1","1","c1");
    locationService.addRoom("b1","1","c2");
    locationService.addRoom("b1","1","c3");
    locationService.addRoom("b1","2","c4");
    locationService.addRoom("b1","2","c5");

    bookingService.bookRoom(new TimeSlot(9F,10F), "c1","1","b1");
    bookingService.getAllBooking();
    bookingService.getAvailableTimeSlots();

  }
}