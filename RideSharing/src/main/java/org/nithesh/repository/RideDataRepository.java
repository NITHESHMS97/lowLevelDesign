package org.nithesh.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.nithesh.exception.RideAlreadyPresent;
import org.nithesh.exception.RideNotFound;
import org.nithesh.model.Ride;

public class RideDataRepository {
  HashMap<String, Ride> rideData;
  HashMap<String, String> vehicleRideData;
  HashMap<String, ArrayList<String>> offeredRide;
  HashMap<String, ArrayList<String>> takenRide;

  public RideDataRepository(){
    rideData = new HashMap<>();
    vehicleRideData = new HashMap<>();
    offeredRide = new HashMap<>();
    takenRide = new HashMap<>();
  }

  public String offerRide(String ownerId, String origin, Integer availableSeats, String model, String regNo, String destination)
      throws RideAlreadyPresent {
    String rideId = ownerId+"_"+ regNo;
    if(vehicleRideData.containsKey(regNo))
      throw new RideAlreadyPresent();
    Ride ride = new Ride(origin, destination, availableSeats, ownerId, model, regNo);
    rideData.put(rideId,ride);
    vehicleRideData.put(regNo,rideId);
    offeredRide.computeIfAbsent(ownerId, k->new ArrayList<>()).add(rideId);
    return rideId;
  }

  public Collection<Ride> getRidesData()
  {
    return rideData.values();
  }

  public void selectRide(String owner, String rideId)
  {
    takenRide.computeIfAbsent(owner, k->new ArrayList<>()).add(rideId);
  }

  public Integer getOfferedRideCount(String owner)
  {
    return offeredRide.computeIfAbsent(owner, k-> new ArrayList<>()).size();
  }

  public Integer getTakenRideCount(String owner)
  {
    return takenRide.computeIfAbsent(owner, k-> new ArrayList<>()).size();
  }

}
