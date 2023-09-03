package org.nithesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Gender;

@Getter
@Setter
@AllArgsConstructor
public class User {
  String username;
  Gender gender;
  Integer age;

}
