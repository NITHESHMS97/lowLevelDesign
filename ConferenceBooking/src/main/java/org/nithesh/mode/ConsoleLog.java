package org.nithesh.mode;

public class ConsoleLog implements   Logger{

  static Logger consoleLog;
  public void logData(String data) {
    System.out.println(data);
  }

  public static  Logger getConsoleLog(){
      if(consoleLog == null)
        consoleLog = new ConsoleLog();
      return consoleLog;
  }
}
