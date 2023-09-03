package org.nithesh.service;

import org.nithesh.Enum.Gender;
import org.nithesh.Enum.SelectRideType;
import org.nithesh.exception.RideAlreadyPresent;
import org.nithesh.exception.RideNotFound;
import org.nithesh.exception.UserDoesNotExists;
import org.nithesh.exception.UserNameAlreadyExists;
import org.nithesh.exception.VehicleAlreadyExists;
import org.nithesh.mode.Print;
import org.nithesh.model.Ride;
import org.nithesh.model.User;
import org.nithesh.repository.RideDataRepository;
import org.nithesh.repository.UserDataRepository;
import org.nithesh.repository.stratergy.SelectRideFactory;

public class RideService {

  RideDataRepository rideDataRepository;
  UserDataRepository userDataRepository;
  Print print;

  public RideService(RideDataRepository rideDataRepository, UserDataRepository userDataRepository,
      Print print)
  {
    this.rideDataRepository = rideDataRepository;
    this.userDataRepository = userDataRepository;
    this.print = print;
  }

  public void registerUser(String username, Gender gender, Integer age)
  {
    try {
      userDataRepository.addUser(username,gender,age);
    } catch (UserNameAlreadyExists e) {
      print.printData("Username already used");
      return;
    }
    print.printData("User registered");
  }
  public void registerVehicle(String owner, String model, String regNo){
    try {
      userDataRepository.addVehicle(owner,model,regNo);
    } catch (UserDoesNotExists e) {
      print.printData("User does not Exists");
      return;
    } catch (VehicleAlreadyExists e) {
      print.printData("Vehicle already registered");
      return;
    }
    print.printData("Vehicle registered");
  }

  public void offerRide(String ownerId, String origin, Integer availableSeats, String model, String regNo, String destination)
  {
    String rideId = null;
    try {
       rideId = rideDataRepository.offerRide(ownerId,origin,availableSeats,model,regNo,destination);
    } catch (RideAlreadyPresent e) {
      print.printData("Ride already added for this vehicle");
    }
    print.printData("ride added: "+ rideId);
  }

  public void selectRide(String owner, String origin, String destination, Integer seats, String model, SelectRideType selectRideType){

    Ride ride = new SelectRideFactory(rideDataRepository).getRide(selectRideType).getRide(origin,destination,seats, model);
    if(ride == null) {
      print.printData("No ride available");
      return;
    }
    rideDataRepository.selectRide(owner, ride.getId());
    print.printData("Ride selected :"+ ride.getId() + " "+ ride.getOwnerId() + " " + ride.getModel() + " " + ride.getAvailableSeats());
  }

  public void printRideStatus(){
    for(User user: userDataRepository.getUserData()){
      String username = user.getUsername();
      print.printData(username + " " + rideDataRepository.getOfferedRideCount(username) + " " + rideDataRepository.getTakenRideCount(username));
    }
    print.printData("--------------");
  }


}
