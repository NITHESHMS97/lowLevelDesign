package org.nithesh;

import org.nithesh.mode.ConsolePrint;
import org.nithesh.mode.Print;
import org.nithesh.repository.BoardDataRepository;
import org.nithesh.repository.CardDataRepository;
import org.nithesh.repository.ListDataRepository;
import org.nithesh.service.BoardService;
import org.nithesh.service.CardService;
import org.nithesh.service.DeleteService;
import org.nithesh.service.ListService;

public class Main {

  public static void main(String[] args) {
    BoardDataRepository boardDataRepository = new BoardDataRepository();
    ListDataRepository listDataRepository = new ListDataRepository();
    CardDataRepository cardDataRepository = new CardDataRepository();
    Print print = new ConsolePrint();
    BoardService boardService = new BoardService(boardDataRepository, print);
    ListService listService = new ListService(listDataRepository, boardDataRepository,print);
    CardService cardService = new CardService(listDataRepository, cardDataRepository, print);
    DeleteService deleteService = new DeleteService(boardDataRepository,listDataRepository,cardDataRepository,print);
    boardService.createBoard("work madu");
    boardService.createBoard("work 2");
    boardService.createBoard("team 1");

    boardService.showBoard(1);
    boardService.updateBoardName(1, "team2");
    boardService.showBoard();

    listService.createList("test1",1);
    listService.createList("test2",2);
    listService.createList("test2",1);
    listService.updateListName(1,"test0");
    listService.showList(1);

    cardService.createCard("card1",1);
    cardService.createCard("card2",1);
    cardService.createCard("card3",1);

    //cardService.moveCard(1,2);
    cardService.showCard(1);

  //  deleteService.deleteCard(1);
     cardService.showCard(1);

    deleteService.deleteBoard(1);
    listService.showList(1);
    listService.showList(2);

    cardService.showCard(2);


  }
}