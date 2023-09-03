package org.nithesh.repository.stratergy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import org.nithesh.model.Ride;
import org.nithesh.repository.RideDataRepository;

public class SelectRideBySeats implements SelectRide {

  RideDataRepository rideDataRepository;

  SelectRideBySeats(RideDataRepository rideDataRepository) {
    this.rideDataRepository = rideDataRepository;
  }

  @Override
  public Ride getRide(String origin, String destination, Integer seats, String model) {
    Collection<Ride> rides = rideDataRepository.getRidesData();
    Ride selectedRide = null;
    Integer maxSeat = 0;
    for (Ride ride : rides) {
      if (Objects.equals(ride.getOrigin(), origin) && Objects.equals(ride.getDestination(),
          destination)) {
        if (ride.getAvailableSeats() > maxSeat) {
          selectedRide = ride;
          maxSeat = ride.getAvailableSeats();
        }
      }

    }
    return selectedRide;
  }
}
