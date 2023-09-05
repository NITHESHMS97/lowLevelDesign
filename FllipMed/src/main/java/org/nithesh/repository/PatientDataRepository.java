package org.nithesh.repository;

import java.util.HashMap;
import org.nithesh.exceptions.PatientAlreadyRegistered;
import org.nithesh.exceptions.PatientNotFound;
import org.nithesh.model.Patient;

public class PatientDataRepository {
  HashMap<String, Patient> patientData;

  private static  PatientDataRepository patientDataRepository;

  private PatientDataRepository(){
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

  synchronized public static PatientDataRepository  getPatientDataRepository(){
    if(patientDataRepository == null)
       patientDataRepository = new PatientDataRepository();
    return patientDataRepository;
  }


}
