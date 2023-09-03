package org.nithesh;

import org.nithesh.Enum.Gender;
import org.nithesh.Enum.SelectRideType;
import org.nithesh.mode.ConsolePrint;
import org.nithesh.mode.Print;
import org.nithesh.repository.RideDataRepository;
import org.nithesh.repository.UserDataRepository;
import org.nithesh.service.RideService;

public class Main {

  public static void main(String[] args) {
    Print print = new ConsolePrint();
    RideDataRepository rideDataRepository = new RideDataRepository();
    UserDataRepository userDataRepository = new UserDataRepository();
    RideService rideService = new RideService(rideDataRepository,userDataRepository, print);
    rideService.registerUser("Nithesh", Gender.M, 21);
    rideService.registerUser("Rahul", Gender.M, 21);
    rideService.registerUser("ratish", Gender.M, 21);
    rideService.registerUser("beena", Gender.F, 21);

    rideService.registerVehicle("Nithesh","baleno","1");
    rideService.registerVehicle("ratish","alto","2");
    rideService.registerVehicle("Nithesh","zoom","3");
    rideService.registerVehicle("ratish","benz","4");

    rideService.offerRide("Nithesh","Banglore",2,"baleno","1","Bellare");
    rideService.offerRide("ratish","Banglore",4,"alto","2","Bellare");

    rideService.offerRide("Nithesh","Banglore",1,"zoom","3","Bellare");

    rideService.offerRide("ratish","Banglore",10,"benz","4","Bellare");
    rideService.selectRide("beena","Banglore","Bellare",1,"baleno", SelectRideType.SEAT);
    rideService.selectRide("beena","Banglore","Bellare",1,"baleno", SelectRideType.SEAT);

  }
}