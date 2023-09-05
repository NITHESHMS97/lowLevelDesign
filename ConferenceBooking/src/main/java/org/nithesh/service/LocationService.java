package org.nithesh.service;

import org.nithesh.exception.BuildingAlreadyExists;
import org.nithesh.exception.BuildingDoesNotExists;
import org.nithesh.exception.ConferenceAlreadyExists;
import org.nithesh.exception.FloorAlreadyExists;
import org.nithesh.exception.FloorDoesNotExists;
import org.nithesh.mode.ConsoleLog;
import org.nithesh.mode.Logger;
import org.nithesh.repository.BookingDataRepository;
import org.nithesh.repository.LocationDataRepository;

public class LocationService {

  Logger logger;
  public LocationService(){
    logger = new ConsoleLog();
  }

  public void addBuilding(String id){
    try {
      LocationDataRepository.getLocationDataRepository().addBuilding(id);
    } catch (BuildingAlreadyExists e) {
      logger.logData("Building already exists");
      return;
    }
    logger.logData("Building added");
  }

  public void addFloor(String buildingId, String floorId){
    try {
      LocationDataRepository.getLocationDataRepository().addFloor(buildingId,floorId);
    } catch (FloorAlreadyExists e) {
      logger.logData("Floor already exists");
      return;
    } catch (BuildingDoesNotExists e) {
      logger.logData("Building does not exists");
      return;
    }
    logger.logData("Floor added");
  }

  public void addRoom(String buildingId, String floorId, String roomId ){
    try {
      LocationDataRepository.getLocationDataRepository().addConferenceRoom(buildingId,floorId,roomId);
      BookingDataRepository.getBookingDataRepository().addAvailableSlotsForRoom(roomId);
    } catch (ConferenceAlreadyExists e) {
      logger.logData("room already exists");
      return;
    } catch (FloorDoesNotExists e) {
      logger.logData("floor does not exist");
      return;
    } catch (BuildingDoesNotExists e) {
      logger.logData("Building does not exists");
      return;
    }
    logger.logData("Room added");
  }


}
