package org.nithesh.repository;

import java.util.ArrayList;
import java.util.HashMap;
import org.nithesh.exception.DeviceNotConnected;
import org.nithesh.exception.InterfaceAlreadyExists;
import org.nithesh.exception.InvalidCommand;
import org.nithesh.exception.SmartHomeDeviceAlreadyExists;
import org.nithesh.model.interfaceDevice.InterfaceDevice;
import org.nithesh.model.interfaceDevice.InterfaceDeviceFactory;
import org.nithesh.model.smartHomeDevice.Fan;
import org.nithesh.model.smartHomeDevice.GenericElectricDevice;
import org.nithesh.model.smartHomeDevice.Light;

public class DeviceDataRepository {
  HashMap<String, InterfaceDevice> interfaceDeviceData;
  HashMap<String, Light>  lightData;
  HashMap<String, Fan> fanData;
  HashMap<String, GenericElectricDevice> genericDeviceData;
  HashMap<String, ArrayList<String>> interfaceSmartDeviceMapping;
  HashMap<String, GenericElectricDevice> electricDeviceData;

  HashMap<String, String> deviceNameMapping;
  static DeviceDataRepository deviceDataRepository;

  public DeviceDataRepository(){
    interfaceDeviceData = new HashMap<>();
    lightData = new HashMap<>();
    fanData = new HashMap<>();
    genericDeviceData = new HashMap<>();
    interfaceSmartDeviceMapping = new HashMap<>();
    electricDeviceData = new HashMap<>();
    deviceDataRepository=null;
  }

  public boolean checkSmartDeviceExist(String id){
    return electricDeviceData.containsKey(id);
  }

  public boolean checkInterfaceDeviceExist(String id){
    return interfaceDeviceData.containsKey(id);
  }

  public GenericElectricDevice getElectricDevice(String id){
    return electricDeviceData.get(id);
  }
   public static synchronized   DeviceDataRepository getInstance(){
    if(deviceDataRepository == null)
      deviceDataRepository = new DeviceDataRepository();
    return deviceDataRepository;
  }

  public Light getLight(String id){
    return lightData.get(id);
  }
  public Fan getFan(String id){
    return fanData.get(id);
  }
  public void addInterfaceDevice(String name, String location , String activationKeyword)
      throws InterfaceAlreadyExists {
    if(interfaceDeviceData.containsKey(activationKeyword))
      throw new InterfaceAlreadyExists();
    InterfaceDevice interfaceDevice = new InterfaceDeviceFactory().getInterfaceDevice(name,
        location, activationKeyword);
    interfaceDeviceData.put(activationKeyword,interfaceDevice);
  }

  public String addSmartHomeDevice(String name, String location, String activationKeyword)
      throws SmartHomeDeviceAlreadyExists {
    String id = name+location;
    if(electricDeviceData.containsKey(id))
      throw new SmartHomeDeviceAlreadyExists();

    if(name.equals("Fan")) {
      Fan fan = new Fan(name, location,activationKeyword);
      fanData.put(id,fan);
      electricDeviceData.put(id,fan);
    }
    else if(name.equals("Light")){
      Light light = new Light(name, location, activationKeyword);
      lightData.put(id, light);
      electricDeviceData.put(id, light);
    }
    else
    {
      GenericElectricDevice genericElectricDevice = new GenericElectricDevice(name, location, activationKeyword);
      genericDeviceData.put(id, genericElectricDevice);
      electricDeviceData.put(id,genericElectricDevice);
    }
    return id;

  }

  public void executeNumericCmd(String id, Integer cmd, String name ) throws InvalidCommand {
    if(name.equals("Fan"))
      fanData.get(id).setFanSpeed(cmd);
    else
      lightData.get(id).setBrightness(cmd);
  }

  public void connectInterfaceToSmartDevice(String keyword, String id) {
    electricDeviceData.get(id).setActivationKey(keyword);
    interfaceSmartDeviceMapping.computeIfAbsent(keyword,k-> new ArrayList<>()).add(id);
  }

  public void disconnectInterfaceToSmartDevice(String keyword, String id)
      throws DeviceNotConnected {
    if(interfaceSmartDeviceMapping.containsKey(keyword) && interfaceSmartDeviceMapping.get(keyword).contains(id)) {
      interfaceSmartDeviceMapping.get(keyword).remove(id);
      electricDeviceData.get(id).setActivationKey(null);
      return;
    }
    throw new DeviceNotConnected();

  }

  public ArrayList<String> getAllConnectedDevice(String keyword) {
    return interfaceSmartDeviceMapping.get(keyword);
  }
}

