package org.nithesh.model.interfaceDevice;

public class InterfaceDeviceFactory {

    public InterfaceDevice getInterfaceDevice(String  name, String location, String keyword){
      if(name.equals("Google Home"))
        return new Google(name,location,keyword);
      else if(name.equals("Alexa"))
        return new Alexa(name, location, keyword);
      return new GenericInterfaceDevice(name, location, keyword);
    }

}
