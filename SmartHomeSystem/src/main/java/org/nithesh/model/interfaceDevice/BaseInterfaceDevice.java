package org.nithesh.model.interfaceDevice;
public class BaseInterfaceDevice implements InterfaceDevice {
  String location;
  String name;
  String activationKey;
  public BaseInterfaceDevice(String name,String location, String activationKey){
    this.location = location;
    this.name = name;
    this.activationKey = activationKey;

  }

  @Override
  public String getlocation() {
    return location;
  }

  @Override
  public String getname() {
    return name;
  }

  @Override
  public String getactivationKey() {
    return activationKey;
  }
}
