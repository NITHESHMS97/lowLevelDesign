package org.nithesh.model.smartHomeDevice;

import lombok.Getter;
import org.nithesh.Enum.Status;

@Getter
public class GenericElectricDevice implements SmartHomeDevice{

  Status status;
  String location;
  String name;
  String activationKey;
  String deviceType;

  public GenericElectricDevice(String name,String location, String activationKey){
    status = Status.OFF;
    this.location = location;
    this.name = name;
    this.activationKey = activationKey;
    deviceType = "ElectricDevice";

  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Status getStatus(){
    return status;
  }
  public String getDeviceType(){
    return deviceType;
  }

  public void setActivationKey(String key){
    this.activationKey = key;
  }
}
