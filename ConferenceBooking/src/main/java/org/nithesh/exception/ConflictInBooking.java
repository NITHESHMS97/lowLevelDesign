package org.nithesh.exception;

public class ConflictInBooking extends RuntimeException{
  public ConflictInBooking(String data){
    super(data);
  }
}
