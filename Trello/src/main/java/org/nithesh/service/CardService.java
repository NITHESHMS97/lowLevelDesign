package org.nithesh.service;

import org.nithesh.exception.CardDoesNotExists;
import org.nithesh.exception.ListDoesNotExists;
import org.nithesh.mode.Print;
import org.nithesh.model.Card;
import org.nithesh.repository.CardDataRepository;
import org.nithesh.repository.ListDataRepository;

public class CardService {

  ListDataRepository listDataRepository;
  CardDataRepository cardDataRepository;
  Print print;

  public CardService(ListDataRepository listDataRepository, CardDataRepository cardDataRepository,
      Print print) {
    this.listDataRepository = listDataRepository;
    this.cardDataRepository = cardDataRepository;
    this.print = print;
  }

  public void createCard(String name, Integer listId) {
    Integer id = null;
    try {
      if (listDataRepository.getListById(listId) != null) {
        id = cardDataRepository.addCard(name, listId);
        listDataRepository.getListById(listId).getCards().add(id);

      }
    } catch (ListDoesNotExists ex) {
      print.printData("List does not exists");
      return;
    }
    print.printData("Card added :"+ id);
  }


  public void updateCardName(Integer id, String name) {

    try {
      cardDataRepository.getCardById(id).setName(name);
    } catch (CardDoesNotExists e) {
      print.printData("Card does not exists");
      return;
    }

    print.printData("Card name changed");
  }

  public void showCard(Integer id)
  {
    Card card = null;

    try {
      card = cardDataRepository.getCardById(id);
    } catch (CardDoesNotExists e) {
      print.printData("Card not found");
      return;
    }

    print.printData("Card :"+ card.getId() + " " + card.getName());
    print.printData("----------------------------");
  }

  public void moveCard(Integer cardId, Integer newListId)
  {
    Card card = null;
    try {
      card = cardDataRepository.getCardById(cardId);
    } catch (CardDoesNotExists e) {
      print.printData("Card doesnot exists");
      return;
    }
    Integer oldListId = card.getListId();
    try {
      listDataRepository.getListById(oldListId).getCards().remove(oldListId);
      listDataRepository.getListById(oldListId).getCards().add(newListId);

    } catch (ListDoesNotExists e) {
      print.printData("List Not found");
      return;
    }
    print.printData("Card moved from "+ oldListId + " to" + newListId);

  }

  public void assginUser(Integer id, String username)
  {
    try {
      cardDataRepository.getCardById(id).setAssignedUser(username);
    } catch (CardDoesNotExists e) {
      print.printData("Card does not Exists");
      return;
    }
    print.printData("Card assignee changed");
  }

  public void unAssginUser(Integer id)
  {
    try {
      cardDataRepository.getCardById(id).setAssignedUser("unassigned");
    } catch (CardDoesNotExists e) {
      print.printData("Card does not Exists");
      return;
    }
    print.printData("Card assignee changed to unassign");
  }

}
