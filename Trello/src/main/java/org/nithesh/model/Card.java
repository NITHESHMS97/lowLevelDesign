package org.nithesh.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

  Integer id;
  String name;
  String description;
  String assignedUser;
  Integer listId;

  public Card(Integer id, String  name, String description, String assignedUser, Integer listId)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.assignedUser = assignedUser;
    this.listId = listId;
  }

}
