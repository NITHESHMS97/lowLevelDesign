package org.nithesh.model;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Speciality;

@Setter
@Getter
public class Booking {
  Integer id;
  String doctor;
  TimeSlot timeSlot;
  String patient;

  public Booking(Integer id, String doctor, TimeSlot timeSlot, String patient ){
    this.id = id;
      this.doctor = doctor;
      this.patient = patient;
      this.timeSlot = timeSlot;
  }
}
