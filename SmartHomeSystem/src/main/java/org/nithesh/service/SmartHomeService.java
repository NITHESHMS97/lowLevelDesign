package org.nithesh.service;

import java.util.ArrayList;
import org.nithesh.Enum.Status;
import org.nithesh.exception.DeviceNotConnected;
import org.nithesh.exception.InterfaceAlreadyExists;
import org.nithesh.exception.InvalidCommand;
import org.nithesh.exception.SmartHomeDeviceAlreadyExists;
import org.nithesh.mode.ConsoleLogger;
import org.nithesh.mode.Logger;
import org.nithesh.model.smartHomeDevice.Fan;
import org.nithesh.model.smartHomeDevice.GenericElectricDevice;
import org.nithesh.model.smartHomeDevice.Light;
import org.nithesh.repository.DeviceDataRepository;
import org.nithesh.constant.Color;


public class SmartHomeService {

  Logger logger;

  public SmartHomeService() {
    logger = ConsoleLogger.getConsoleLogger();
  }

  public void printConnectedDevice(String keyword, String location){
    DeviceDataRepository deviceDataRepository = DeviceDataRepository.getInstance();
    if(!deviceDataRepository.checkInterfaceDeviceExist(keyword)){
      logger.logData("Interface device not found");
      return;
    }
    ArrayList<String> devices =  deviceDataRepository.getAllConnectedDevice(keyword);

    for(String device : devices){
      String deviceType = deviceDataRepository.getElectricDevice(device).getDeviceType();

      if(deviceType.equals("Light")){
        Light  light = deviceDataRepository.getLight(device);
        logger.logData(device + " " + light.getName() +" " + light.getStatus() + " " + light.getBrightness() + " "+ light.getColor() );
      }
      else if(deviceType.equals("Fan")){
        Fan fan = deviceDataRepository.getFan(device);
        logger.logData(device + " "+ fan.getName() + " " + fan.getStatus() +" "+ fan.getFanSpeed());
      }
      else{
        GenericElectricDevice genericElectricDevice = deviceDataRepository.getElectricDevice(device);
        logger.logData(device+ " " + genericElectricDevice.getStatus() + " " + genericElectricDevice.getName());
      }

    }
    logger.logData("--------------------------------------------------------");

  }
  public void addInterfaceDevice(String name, String location, String activationKeyword) {
    try {
      DeviceDataRepository.getInstance().addInterfaceDevice(name, location, activationKeyword);
    } catch (InterfaceAlreadyExists e) {
      logger.logData("Interface already exists");
      return;
    }
    logger.logData("Interface device added :" + name);
  }

  public void addSmartHomeDevice(String name, String location, String activationKeyword) {
    String id = null;
    try {
      id = DeviceDataRepository.getInstance().addSmartHomeDevice(name, location, activationKeyword);
    } catch (SmartHomeDeviceAlreadyExists e) {
      logger.logData("Smart home device already exist");
      return;
    }
    logger.logData("Smart home device added :" + id);
  }

  public void connectDevice(String keyword,String name, String location){
    DeviceDataRepository deviceDataRepository = DeviceDataRepository.getInstance();
    String id = name+location;
    if (!deviceDataRepository.checkSmartDeviceExist(id)) {
      logger.logData("Device does not exists");
      return;
    }
    if(!deviceDataRepository.checkInterfaceDeviceExist(keyword)){
      logger.logData("Interface device not found");
      return;
    }
    deviceDataRepository.connectInterfaceToSmartDevice(keyword,id);
    logger.logData("device connected");
  }

  public void disconnectDevice(String keyword,String name, String location){
    DeviceDataRepository deviceDataRepository = DeviceDataRepository.getInstance();
    String id = name+location;
    if (!deviceDataRepository.checkSmartDeviceExist(id)) {
      logger.logData("Device does not exists");
      return;
    }
    if(deviceDataRepository.checkInterfaceDeviceExist(keyword)){
      logger.logData("Interface device not found");
      return;
    }
    try {
      deviceDataRepository.disconnectInterfaceToSmartDevice(keyword,id);
    } catch (DeviceNotConnected e) {
      logger.logData("Device not connected");
      return;
    }
    logger.logData("Device disconnected");

  }
  public void giveCommand(String keyword, String name, String location, String cmd) {
    DeviceDataRepository deviceDataRepository = DeviceDataRepository.getInstance();
    String id = name + location;
    if (!deviceDataRepository.checkSmartDeviceExist(id)) {
      logger.logData("Device does not exists");
      return;
    }
    if(deviceDataRepository.getElectricDevice(id).getActivationKey().equalsIgnoreCase(keyword)){
      logger.logData("Sorry did you say :"+ deviceDataRepository.getElectricDevice(id).getActivationKey() );
      return;
    }
    if (cmd.equalsIgnoreCase("ON") || cmd.equalsIgnoreCase("OFF")) {
      deviceDataRepository.getElectricDevice(id).setStatus(Status.valueOf(cmd.toUpperCase()));
      logger.logData("Command executed");
      return;
    }
    if (Integer.parseInt(cmd) >= 0 && Integer.parseInt(cmd) <= 10) {
      try {
        deviceDataRepository.executeNumericCmd(id, Integer.parseInt(cmd), name);
      } catch (InvalidCommand e) {
        logger.logData("Invalid command :" + cmd);
      }
      logger.logData("Command executed");
      return;
    }

    if (Color.color.contains(cmd.toLowerCase())){
      deviceDataRepository.getLight(id).setColor(cmd.toLowerCase());
      return;
    }
    logger.logData("Invalid command");
  }

}

