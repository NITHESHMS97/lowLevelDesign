package org.nithesh.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.exception.MemberAlreadyExists;
import org.nithesh.exception.MemberDoesNotExists;

@Setter
@Getter
public class List {
  Integer id;
  String name;
  ArrayList<Integer> cards;
  Integer boardId;

  public List(Integer id, String name, Integer boardId)
  {
    this.id = id;
    this.name = name;
    cards =  new ArrayList<>();
    this.boardId = boardId;
  }

  public ArrayList<Integer> getCards(){
    return cards;
  }

  public void addCard(Integer cardId) throws MemberAlreadyExists {
    if(cards.contains(cardId))
      throw new MemberAlreadyExists();
    cards.add(cardId);
  }

  public void removeCard(Integer cardId) throws MemberDoesNotExists {
    if(!cards.contains(cardId))
      throw new MemberDoesNotExists();
    cards.remove(cardId);
  }

}
