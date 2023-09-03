package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.exception.ListDoesNotExists;
import org.nithesh.model.List;

public class ListDataRepository {
  HashMap<Integer, List> listData;
  Integer listIdGenerator;

  Integer getListId(){
    listIdGenerator+=1;
    return listIdGenerator;
  }
  public ListDataRepository(){
    listData = new HashMap<>();
    listIdGenerator =0;
  }

  public Integer addList(String name, Integer boardId){
    Integer id = getListId();
    List list = new List(id, name, boardId);
    listData.put(id,list);
    return id;
  }

  public List getListById(Integer id) throws ListDoesNotExists {
    if(listData.containsKey(id))
      return listData.get(id);
    throw new ListDoesNotExists();
  }

  public void removeList(Integer listId) {
    if(listData.containsKey(listId)){
      listData.remove(listId);
      return;
    }
    listData.remove(listId);
  }
}
