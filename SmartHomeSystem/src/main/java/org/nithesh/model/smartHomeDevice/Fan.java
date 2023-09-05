package org.nithesh.model.smartHomeDevice;

import org.nithesh.exception.InvalidCommand;

public class Fan extends GenericElectricDevice{

   Integer fanSpeed;
   final Integer MIN_SPEED = 1;
   final Integer MAX_SPEED = 5;


  public Fan(String name, String location, String activationKey) {
    super(name, location, activationKey);
    fanSpeed = MIN_SPEED;
    deviceType = "Fan";
  }

  public Integer getFanSpeed(){
    return fanSpeed;
  }
  public void setFanSpeed(Integer fanSpeed) throws InvalidCommand {
    if(fanSpeed < MIN_SPEED || fanSpeed > MAX_SPEED)
      throw new InvalidCommand();
    this.fanSpeed = fanSpeed;
  }
}
