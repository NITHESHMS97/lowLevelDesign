package com.nithesh.services;

import com.nithesh.models.ExpenseType;
import com.nithesh.models.User;
import com.nithesh.repository.UserDataRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseManager {

 // ArrayList<ArrayList<Integer>> expenseTable;
  int expenseTable[][];
  HashMap<Integer, User> userData;
  UserDataRepository userDataRepository;
  int noOfUser;

  public ExpenseManager(int noOfUsers) {
    this.noOfUser = noOfUsers;
    expenseTable = new int[noOfUsers+1][noOfUsers+1];
    userDataRepository = new UserDataRepository();
    userData = userDataRepository.initialiseUser(noOfUsers);
  }

  public void showAllBalance() {
    int flag = 0;
    for (int i = 1; i <= this.noOfUser; i++) {
      for (int j = 1; j <= this.noOfUser; j++) {
        int difference = expenseTable[i][j];
        if (difference - expenseTable[j][i] > 0) {
          flag = 1;
          System.out.println(
              userData.get(i).getName() + " owes " + Integer.toString(difference) + " to "
                  + userData.get(j).getName());
        }
      }
    }
    if (flag == 0) {
      System.out.println("no balance");
    }
  }

  public void showUserBalance(int userId) {
    int flag = 0;
    for (int i = 1; i <= this.noOfUser; i++) {
      int difference = expenseTable[userId][i];
      if (difference - expenseTable[i][userId] > 0) {
        flag = 1;
        System.out.println(
            userData.get(userId).getName() + " owes " + Integer.toString(difference) + " to "
                + userData.get(i).getName());
      }
    }
    if (flag == 0) {
      System.out.println("no balance");
    }

  }

  public void addExpense(int payeeUid, int totalExpense, int noOfUsers, ArrayList<Integer> users,
      ExpenseType expenseType, ArrayList<Integer> costSharing) {
    switch(expenseType){
      case EQUAL:
        addEqualExpense(payeeUid,totalExpense,noOfUsers,users,costSharing);
        break;
      case EXACT:
        addExactExpense(payeeUid,noOfUsers,users,costSharing);
        break;
      case PERCENT:
        addPercentExpense(payeeUid,totalExpense,noOfUsers,users,costSharing);
        break;
    }

  }

  private void addPercentExpense(int payeeUid, int totalExpense, int noOfUsers, ArrayList<Integer> users,
       ArrayList<Integer> costSharing) {
    for(int i=0;i< noOfUsers;i++)
      if(payeeUid!=users.get(i))
        expenseTable[users.get(i)][payeeUid] += totalExpense*(costSharing.get(i)/100);
  }

  private void addExactExpense(int payeeUid, int noOfUsers, ArrayList<Integer> users,
      ArrayList<Integer> costSharing) {
    for(int i=0;i< noOfUsers;i++)
      expenseTable[users.get(i)][payeeUid] += costSharing.get(i);
  }

  private void addEqualExpense(int payeeUid, int totalExpense, int noOfUsers, ArrayList<Integer> users,
      ArrayList<Integer> costSharing) {
    int shareCost = totalExpense/noOfUsers;
    for(Integer user: users){
      if(user!=payeeUid)
        expenseTable[user][payeeUid] += shareCost;
    }
  }
}
