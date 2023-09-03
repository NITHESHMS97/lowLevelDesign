package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.exception.CardDoesNotExists;
import org.nithesh.model.Card;

public class CardDataRepository {
  HashMap<Integer, Card>  cardData;
  Integer cardIdGenerator;

  Integer getCardId(){
    cardIdGenerator+=1;
    return cardIdGenerator;
  }
  public CardDataRepository(){
    cardIdGenerator = 0;
    cardData = new HashMap<>();
  }

  public Integer addCard(String  name, Integer listId){
    Integer id = getCardId();
    Card card = new Card(id, name, "", "unassigned",listId);
    cardData.put(id,card);
    return id;
  }

  public Card getCardById(Integer id) throws CardDoesNotExists {
    if(cardData.containsKey(id))
      return cardData.get(id);
    throw new CardDoesNotExists();
  }


  public void removeCard(Integer cardId) throws CardDoesNotExists {
    if(cardData.containsKey(cardId)) {
      cardData.remove(cardId);
      return;
    }
    throw new CardDoesNotExists();
  }
}
