package org.nithesh.service;

import java.util.ArrayList;
import org.nithesh.exception.BoardNotFound;
import org.nithesh.exception.CardDoesNotExists;
import org.nithesh.exception.ListDoesNotExists;
import org.nithesh.mode.Print;
import org.nithesh.repository.BoardDataRepository;
import org.nithesh.repository.CardDataRepository;
import org.nithesh.repository.ListDataRepository;

public class DeleteService {

  BoardDataRepository boardDataRepository;
  ListDataRepository listDataRepository;
  CardDataRepository cardDataRepository;
  Print print;

  public DeleteService(BoardDataRepository boardDataRepository, ListDataRepository listDataRepository, CardDataRepository cardDataRepository, Print print)
  {
    this.boardDataRepository = boardDataRepository;
    this.cardDataRepository = cardDataRepository;
    this.listDataRepository = listDataRepository;
    this.print = print;
  }

  public void deleteBoard(Integer boardId){
    ArrayList<Integer> lists = null;
    try {
      lists = boardDataRepository.getBoardById(boardId).getList();
      boardDataRepository.removeBoard(boardId);
    } catch (BoardNotFound e) {
      print.printData("Board not found");
      return;
    }
    for(Integer listId : lists){
      deleteList(listId);
    }
    print.printData("Deleted board" + boardId);
  }

  public void deleteList(Integer listId) {
    ArrayList<Integer> cards = null;
    Integer boardId = null;
    try {
      cards = listDataRepository.getListById(listId).getCards();
      listDataRepository.removeList(listId);

    } catch (ListDoesNotExists e) {
      print.printData("List does not exists");
      return;
    }
    for(Integer cardId : cards){
      deleteCard(cardId);
    }
    print.printData("list delete" + listId);

  }

  public void deleteCard(Integer cardId) {
    try {
      cardDataRepository.removeCard(cardId);
    } catch (CardDoesNotExists e) {
      print.printData("Card does not exists");
      return;
    }
    print.printData("card deleted"+ cardId);

  }

}
