package org.nithesh.model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.nithesh.Enum.Privacy;
import org.nithesh.exception.ListAlreadyExists;
import org.nithesh.exception.ListDoesNotExists;
import org.nithesh.exception.MemberAlreadyExists;
import org.nithesh.exception.MemberDoesNotExists;

@Getter
@Setter
public class Board {
  Integer id;
  String name;
  Privacy privacy;
  String url;
  ArrayList<String> member;
  ArrayList<Integer> lists;

  public  Board(Integer id, String name, Privacy privacy, String url)
  {
    this.id = id;
    this.name = name;
    this.privacy = privacy;
    this.url = url;
    member = new ArrayList<>();
    lists = new ArrayList<>();
  }

  public void addMember(String username) throws MemberAlreadyExists {
    if(member.contains(username))
      throw new MemberAlreadyExists();
    member.add(username);
  }

  public ArrayList<Integer> getList(){
    return lists;
  }

  public void removeMember(String username) throws MemberDoesNotExists {
    if(!member.contains(username))
      throw new MemberDoesNotExists();
    member.remove(username);
  }

  public void addList(Integer listId) throws ListAlreadyExists {
    if(lists.contains(listId))
      throw new ListAlreadyExists();
    lists.add(listId);
  }

  public void removeList(Integer listId) throws ListDoesNotExists {
    if(!lists.contains(listId))
      throw new ListDoesNotExists();
    lists.remove(listId);
  }


}
