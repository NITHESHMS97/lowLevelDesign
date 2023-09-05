package org.nithesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
  Integer id;
  String conferenceId;
  TimeSlot timeSlot;

}
