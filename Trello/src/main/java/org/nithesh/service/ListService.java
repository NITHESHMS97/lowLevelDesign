package org.nithesh.service;

import org.nithesh.Enum.Privacy;
import org.nithesh.exception.BoardNotFound;
import org.nithesh.exception.ListDoesNotExists;
import org.nithesh.mode.Print;
import org.nithesh.model.Board;
import org.nithesh.model.List;
import org.nithesh.repository.BoardDataRepository;
import org.nithesh.repository.ListDataRepository;

public class ListService {

  ListDataRepository listDataRepository;
  BoardDataRepository boardDataRepository;
  Print print;

  public ListService(ListDataRepository listDataRepository, BoardDataRepository boardDataRepository,
      Print print) {
    this.listDataRepository = listDataRepository;
    this.boardDataRepository = boardDataRepository;
    this.print = print;
  }

  public void createList(String name, Integer boardId) {
    Integer id =null;
    try {
      if (boardDataRepository.getBoardById(boardId) != null) {
        id = listDataRepository.addList(name, boardId);
        boardDataRepository.getBoardById(boardId).getList().add(id);

      }
    } catch (BoardNotFound ex) {
        print.printData("Board not found");
        return;
    }
    print.printData("List created :"+ id);
  }


  public void updateListName(Integer id, String name) {

    try {
      listDataRepository.getListById(id).setName(name);
    } catch (ListDoesNotExists e) {
      print.printData("List does not exists");
    }

    print.printData("Card name changed");
  }

  public void showList(Integer id)
  {
    List list = null;

    try {
      list = listDataRepository.getListById(id);
    } catch (ListDoesNotExists e) {
      print.printData("List not found");
      return;
    }

    print.printData("List :"+ list.getId() + " " + list.getName());
    print.printData("----------------------------");
  }
}

