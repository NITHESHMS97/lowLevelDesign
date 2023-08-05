package com.nithesh;

import static com.nithesh.models.Command.EXPENSE;
import static com.nithesh.models.ExpenseType.EQUAL;

import com.nithesh.models.Command;
import com.nithesh.models.ExpenseType;
import com.nithesh.services.ExpenseManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static int getId(String s){
    return Integer.parseInt(String.valueOf(s.charAt(1)));
  }
  public static void main(String[] args) {
    ExpenseManager expenseManager = new ExpenseManager(4);
    Scanner scan = new Scanner(System.in);
    while(true){
      String cmd = scan.nextLine();
      String[] token = cmd.split(" ");


      Command command = Command.valueOf(token[0]);
      switch(command){
        case SHOW:{
          if(token.length==1)
            expenseManager.showAllBalance();
          else
            expenseManager.showUserBalance(Integer.parseInt(String.valueOf(token[1].charAt(1))));
          break;
        }
        case EXPENSE: {
          int payeeUid = getId(token[1]);
          int totalExpense = Integer.parseInt(token[2]);
          int noOfUsers =  Integer.parseInt(token[3]);
          ArrayList<Integer> users = new ArrayList<>(noOfUsers);
          for(int i=0;i<noOfUsers;i++){
            users.add(getId(token[4+i]));
          }
          int index = noOfUsers + 4;
          ExpenseType expenseType = ExpenseType.valueOf(token[index]);
          ArrayList<Integer> costSharing = new ArrayList<>(noOfUsers);
          if(expenseType !=EQUAL){
            for(int i=0;i<noOfUsers;i++) {
              costSharing.add(Integer.parseInt(token[index + 1+ i]));
            }
          }
          expenseManager.addExpense(payeeUid, totalExpense, noOfUsers, users, expenseType, costSharing);
          break;
        }
        case EXIT:
          return;
      }

  }
}
}