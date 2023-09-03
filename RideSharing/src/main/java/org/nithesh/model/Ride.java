package org.nithesh.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ride {
  String id;
  String origin;
  String destination;
  Integer availableSeats;
  String ownerId;
  String model;
  String regNo;

  public Ride(String origin, String destination, Integer availableSeats, String ownerId, String model, String regNo)
  {
    id = ownerId +"_"+ regNo;
    this.origin = origin;
    this.destination = destination;
    this.availableSeats = availableSeats;
    this.ownerId = ownerId;
    this.model = model;
    this.regNo = regNo;
  }


}
