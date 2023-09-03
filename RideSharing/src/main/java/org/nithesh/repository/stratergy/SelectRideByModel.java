package org.nithesh.repository.stratergy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import org.nithesh.model.Ride;
import org.nithesh.repository.RideDataRepository;

public class SelectRideByModel implements  SelectRide{

  RideDataRepository rideDataRepository;

  SelectRideByModel(RideDataRepository rideDataRepository){
    this.rideDataRepository = rideDataRepository;
  }

  @Override
  public Ride getRide(String origin, String destination, Integer seats, String model){
    Collection<Ride> rides = rideDataRepository.getRidesData();
    for(Ride ride : rides){
      if(Objects.equals(ride.getModel(), model)
          && Objects.equals(ride.getOrigin(), origin) && Objects.equals(ride.getDestination(),
          destination))
        return ride;
    }
    return null;
  }

}
