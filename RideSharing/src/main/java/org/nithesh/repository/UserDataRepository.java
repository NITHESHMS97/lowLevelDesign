package org.nithesh.repository;

import java.util.ArrayList;
import java.util.HashMap;
import org.nithesh.Enum.Gender;
import org.nithesh.Enum.Status;
import org.nithesh.exception.UserNameAlreadyExists;
import org.nithesh.exception.UserDoesNotExists;
import org.nithesh.exception.VehicleAlreadyExists;

import org.nithesh.model.User;
import org.nithesh.model.Vehicle;


public class UserDataRepository {
  HashMap<String, User> userData;
  HashMap<String, Vehicle> vehicleData;
  HashMap<String, ArrayList<String>> userVehicleData;

  public UserDataRepository(){
    userData = new HashMap<>();
    vehicleData = new HashMap<>();
    userVehicleData = new HashMap<>();
  }

  public void addUser(String username, Gender gender, Integer age) throws UserNameAlreadyExists {
    if(userData.containsKey(username))
      throw new UserNameAlreadyExists();
    User  user = new User(username, gender, age);
    userData.put(username, user);
  }

  public void addVehicle(String owner, String model, String regNo)
      throws UserDoesNotExists, VehicleAlreadyExists {
    if(!userData.containsKey(owner))
      throw new UserDoesNotExists();
    Vehicle vehicle = new Vehicle(owner, model, regNo, Status.AVAILABLE);
    if(vehicleData.containsKey(regNo))
        throw new VehicleAlreadyExists();
    vehicleData.put(regNo,vehicle);
    userVehicleData.computeIfAbsent(owner,k->new ArrayList<>()).add(regNo);

  }

  public ArrayList<User> getUserData(){
    return (ArrayList<User>) userData.values();
  }
}
