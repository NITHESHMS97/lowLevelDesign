package org.nithesh.model.smartHomeDevice;

import org.nithesh.Enum.Status;

public interface SmartHomeDevice {
  public void setStatus(Status status);

  public Status getStatus();
}
