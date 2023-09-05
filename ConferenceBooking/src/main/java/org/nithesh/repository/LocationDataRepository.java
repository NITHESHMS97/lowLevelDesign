package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.exception.BuildingAlreadyExists;
import org.nithesh.exception.BuildingDoesNotExists;
import org.nithesh.exception.ConferenceAlreadyExists;
import org.nithesh.exception.ConferenceDoestNotExists;
import org.nithesh.exception.FloorAlreadyExists;
import org.nithesh.exception.FloorDoesNotExists;
import org.nithesh.model.Building;
import org.nithesh.model.ConferenceRoom;
import org.nithesh.model.Floor;

public class LocationDataRepository {

  HashMap<String, Building> buildingData;
  HashMap<String, Floor> floorData;
  HashMap<String, ConferenceRoom> conferenceRoomData;

  static LocationDataRepository locationDataRepository=null;
  private  LocationDataRepository(){
    buildingData = new HashMap<String, Building>();
    floorData = new HashMap<String, Floor>();
    conferenceRoomData = new HashMap<String, ConferenceRoom>();
  }

  public ConferenceRoom getRoomById(String id) throws ConferenceDoestNotExists {
    if(conferenceRoomData.containsKey(id))
      return conferenceRoomData.get(id);
    throw new ConferenceDoestNotExists();
  }

  public boolean checkBuildingExists(String id){
    return buildingData.containsKey(id);
  }
  public boolean checkFloorExists(String id){
    return floorData.containsKey(id);
  }
  public boolean checkRoomExists(String id){
    return conferenceRoomData.containsKey(id);
  }

  public static LocationDataRepository  getLocationDataRepository(){
    if(locationDataRepository==null)
      locationDataRepository = new LocationDataRepository();
    return locationDataRepository;
  }

  public void addBuilding(String buildingId) throws BuildingAlreadyExists {
    if(buildingData.containsKey(buildingId))
      throw new BuildingAlreadyExists();
    buildingData.put(buildingId,new Building(buildingId));
  }

  public void addFloor(String buildingId, String floorId) throws FloorAlreadyExists, BuildingDoesNotExists {
    if(floorData.containsKey(floorId))
      throw new FloorAlreadyExists();
    if(!buildingData.containsKey(buildingId))
      throw new BuildingDoesNotExists();
    floorData.put(floorId,new Floor(floorId,buildingId));
  }
  public void addConferenceRoom(String buildingId, String floorId, String roomId)
      throws ConferenceAlreadyExists, FloorDoesNotExists, BuildingDoesNotExists {
    if(conferenceRoomData.containsKey(roomId))
      throw new ConferenceAlreadyExists();
    if(!floorData.containsKey(floorId))
      throw new FloorDoesNotExists();
    if(!buildingData.containsKey(buildingId))
      throw new BuildingDoesNotExists();
    conferenceRoomData.put(roomId,new ConferenceRoom(roomId,floorId,buildingId));
  }


}
