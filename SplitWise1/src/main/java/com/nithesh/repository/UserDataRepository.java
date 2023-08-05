package com.nithesh.repository;

import com.nithesh.models.User;
import java.util.HashMap;

public class UserDataRepository {

  HashMap<Integer, User> userData;
  public UserDataRepository(){
    userData = new HashMap<Integer,User>();
  }
  public HashMap<Integer, User> initialiseUser(int noOfUsers) {

    for(int i=1;i<=noOfUsers; i++){
      String userName = "User"+Integer.toString(i);
      User user = new User(i, userName,userName+"@gmail.com","12345678");
      userData.put(i, user);
    }
    return userData;
  }
}
