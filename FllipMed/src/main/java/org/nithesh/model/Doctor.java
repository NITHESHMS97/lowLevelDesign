package org.nithesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Speciality;

@Getter
@Setter
@AllArgsConstructor
public class Doctor {

  String username;
  Speciality speciality;
  Integer rating;
}
