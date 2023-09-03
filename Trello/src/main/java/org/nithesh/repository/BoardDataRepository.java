package org.nithesh.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.nithesh.Enum.Privacy;
import org.nithesh.exception.BoardNotFound;
import org.nithesh.model.Board;

public class BoardDataRepository {
  HashMap<Integer, Board> boardData;
  Integer noOfBoards;

  Integer getBoardId(){
    noOfBoards+=1;
    return noOfBoards;
  }

  public BoardDataRepository(){
    this.boardData = new HashMap<>();
    noOfBoards =0;
  }

  public Integer createBoard(String name){
    Integer id = getBoardId();
    Board board = new Board(id, name, Privacy.PUBLIC, Integer.toString(id));
    boardData.put(id, board);
    return id;
  }

  public Board getBoardById(Integer id) throws BoardNotFound {
    if(boardData.containsKey(id))
      return boardData.get(id);
    throw new BoardNotFound();
  }
  public boolean checkBoardExists(Integer id)  {
    return boardData.containsKey(id);
  }

  public Collection<Board> getBoards(){
    return boardData.values();
  }

  public void removeBoard(Integer id) throws BoardNotFound {
    if(boardData.containsKey(id)){
      boardData.remove(id);
      return;
    }

    throw new BoardNotFound();
  }
}
