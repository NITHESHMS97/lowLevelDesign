package org.nithesh.repository.stratergy;

import java.util.HashMap;
import org.nithesh.Enum.SelectRideType;
import org.nithesh.repository.RideDataRepository;

public class SelectRideFactory {

  HashMap<SelectRideType, SelectRide>  factory = new HashMap<>();

  public SelectRideFactory(RideDataRepository rideDataRepository){
    factory.put(SelectRideType.MODEL, new SelectRideByModel(rideDataRepository));
    factory.put(SelectRideType.SEAT, new SelectRideBySeats(rideDataRepository));
  }

  public SelectRide  getRide(SelectRideType selectRideType)
  {
    return factory.get(selectRideType);
  }

}
