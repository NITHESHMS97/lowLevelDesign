package org.nithesh.service;

import java.util.Collection;
import org.nithesh.Enum.Privacy;
import org.nithesh.exception.BoardNotFound;
import org.nithesh.mode.Print;
import org.nithesh.model.Board;
import org.nithesh.repository.BoardDataRepository;

public class BoardService {
  BoardDataRepository boardDataRepository;
  Print print;
  public BoardService(BoardDataRepository boardDataRepository, Print print){
    this.boardDataRepository = boardDataRepository;
    this.print =  print;
  }

  public  void createBoard(String name){
    Integer id = boardDataRepository.createBoard(name);
    print.printData("Board created :" + id);
    return;
  }

  public void updateBoardName(Integer id, String name){
    try {
      boardDataRepository.getBoardById(id).setName(name);
    } catch (BoardNotFound e) {
      print.printData("Board not found");
      return;
    }
    print.printData("Board privacy changed");
  }

  public void updateBoardPrivacy(Integer id, Privacy privacy){
    try {
      boardDataRepository.getBoardById(id).setPrivacy(privacy);
    } catch (BoardNotFound e) {
      print.printData("Board not found");
    }
    print.printData("Board name changed");
  }

  public void showBoard(Integer id)
  {
    Board board = null;
    try {
      board = boardDataRepository.getBoardById(id);
    } catch (BoardNotFound e) {
      print.printData("Board not found");
      return;
    }
    print.printData("Board :"+ board.getId() + " " + board.getName() + " " + board.getPrivacy());
    print.printData("----------------------------");
  }
  public void showBoard()
  {
    Collection<Board> boards = null;
    boards = boardDataRepository.getBoards();
    for(Board board : boards)
      print.printData("Board :"+ board.getId() + " " + board.getName() + " " + board.getPrivacy());
    print.printData("----------------------------");
  }

}
