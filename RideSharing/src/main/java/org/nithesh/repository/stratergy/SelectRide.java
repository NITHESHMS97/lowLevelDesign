package org.nithesh.repository.stratergy;

import java.util.ArrayList;
import java.util.Objects;
import org.nithesh.model.Ride;
import org.nithesh.repository.RideDataRepository;

public interface SelectRide{

  Ride getRide(String origin, String destination, Integer seats, String model);

}
