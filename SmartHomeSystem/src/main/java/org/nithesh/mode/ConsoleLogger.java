package org.nithesh.mode;

public class ConsoleLogger implements  Logger{


  static ConsoleLogger consoleLogger;
  @Override
  public void logData(String data) {
    System.out.println(data);
  }

  public static ConsoleLogger getConsoleLogger(){
    if(consoleLogger==null)
      consoleLogger = new ConsoleLogger();
    return consoleLogger;
  }
}
