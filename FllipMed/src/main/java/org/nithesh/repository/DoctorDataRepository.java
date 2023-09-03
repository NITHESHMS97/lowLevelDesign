package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.Enum.Speciality;
import org.nithesh.exceptions.DoctorAlreadyRegisterd;
import org.nithesh.exceptions.DoctorNotFound;
import org.nithesh.model.Doctor;

public class DoctorDataRepository {
  HashMap<String, Doctor> doctorData;

  public DoctorDataRepository(){
    doctorData = new HashMap<>();
  }

  public void addDoctor(String username, Speciality speciality) throws DoctorAlreadyRegisterd {
    if(doctorData.containsKey(username))
      throw new DoctorAlreadyRegisterd();
    Doctor doctor = new Doctor(username,speciality,0);
    doctorData.put(username,doctor);

  }

  public Doctor getDoctorById(String username) throws DoctorNotFound {
    if(doctorData.containsKey(username))
      return doctorData.get(username);
    throw new DoctorNotFound();
  }
}
