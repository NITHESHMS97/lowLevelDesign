package org.nithesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Speciality;
import org.nithesh.Enum.Status;

@Getter
@Setter
@AllArgsConstructor
public class Availabiltiy {
  String id;
  TimeSlot timeSlot;
  String doctor;
  Status status;



}
