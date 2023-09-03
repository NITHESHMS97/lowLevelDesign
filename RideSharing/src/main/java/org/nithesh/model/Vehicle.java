package org.nithesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Status;

@Getter
@Setter
@AllArgsConstructor
public class Vehicle {
  String regNo;
  String ownerId;
  String model;
  Status status;


}
