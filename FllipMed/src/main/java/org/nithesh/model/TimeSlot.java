package org.nithesh.model;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class TimeSlot {
  LocalTime startTime;
  LocalTime endTime;
  @Override
  public String toString() {
    return startTime + "_"+endTime;
  }

}
