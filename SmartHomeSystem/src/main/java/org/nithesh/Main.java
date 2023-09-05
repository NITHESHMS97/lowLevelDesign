package org.nithesh;

import org.nithesh.model.smartHomeDevice.SmartHomeDevice;
import org.nithesh.service.SmartHomeService;

public class Main {

  public static void main(String[] args) {
    SmartHomeService smartHomeService = new SmartHomeService();
    smartHomeService.addInterfaceDevice("Google Home", "Living Room", "OK Google");
    smartHomeService.addInterfaceDevice("Alexa", "Living Room", "Alexa");

    smartHomeService.addSmartHomeDevice("Fan","Living Room","OK Google");
    smartHomeService.addSmartHomeDevice("Light","Living Room","Alexa");
    smartHomeService.addSmartHomeDevice("Charger",null,null);

    smartHomeService.connectDevice("OK Google","Fan", "Living Room");
    smartHomeService.connectDevice("OK Google","Light", "Living Room");
    smartHomeService.connectDevice("OK Google","Charger", null);

    smartHomeService.printConnectedDevice("OK Google", "Living Room");

    smartHomeService.giveCommand("OK Google","Fan","Living Room", "4");
    smartHomeService.giveCommand("OK Google","Fan","Living Room", "10");
 //   smartHomeService.giveCommand("OK Google","Light","Living Room", "red");
    smartHomeService.giveCommand("OK Google","Light","Living Room", "ON");
    smartHomeService.printConnectedDevice("OK Google", "Living Room");



  }
}