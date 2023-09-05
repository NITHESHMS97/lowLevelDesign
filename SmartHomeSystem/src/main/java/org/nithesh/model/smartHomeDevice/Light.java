package org.nithesh.model.smartHomeDevice;

import org.nithesh.exception.InvalidCommand;

public class Light  extends  GenericElectricDevice{
   Integer brightness;
   String color;
   final Integer MIN_BRIGHTNESS = 1;
   final Integer MAX_BRIGHTNESS = 10;

   final String DEFAULT_COLOR = "white";

  public Light(String name, String location, String activationKey) {
    super(name, location, activationKey);
    brightness = MIN_BRIGHTNESS;
    color = DEFAULT_COLOR;
    deviceType = "Light";

  }

  public Integer getBrightness(){
    return brightness;
  }
  public void setBrightness(Integer brightness) throws InvalidCommand {
    if (brightness < MIN_BRIGHTNESS || brightness > MAX_BRIGHTNESS)
      throw new InvalidCommand();
    this.brightness = brightness;
  }

  public void setColor(String color){
    this.color = color;
  }
  public String getColor(){
    return color;
  }

}
