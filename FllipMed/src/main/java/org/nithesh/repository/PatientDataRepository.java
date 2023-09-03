package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.exceptions.DoctorNotFound;
import org.nithesh.exceptions.PatientAlreadyRegistered;
import org.nithesh.exceptions.PatientNotFound;
import org.nithesh.model.Booking;
import org.nithesh.model.Doctor;
import org.nithesh.model.Patient;

public class PatientDataRepository {
  HashMap<String, Patient> patientData;

  public PatientDataRepository(){
    patientData = new HashMap<>();
  }

  public void addPatient(String username) throws PatientAlreadyRegistered {
    if(patientData.containsKey(username))
      throw new PatientAlreadyRegistered();
    Patient patient = new Patient(username);
    patientData.put(username,patient);
  }

  public Patient getPatientById(String username) throws PatientNotFound {
    if(patientData.containsKey(username))
      return patientData.get(username);
    throw new PatientNotFound();
  }


}
