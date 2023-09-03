package org.nithesh.service;

import org.nithesh.Enum.Speciality;
import org.nithesh.exceptions.DoctorAlreadyRegisterd;
import org.nithesh.exceptions.PatientAlreadyRegistered;
import org.nithesh.mode.Print;
import org.nithesh.repository.DoctorDataRepository;
import org.nithesh.repository.PatientDataRepository;

public class RegisterService {

  DoctorDataRepository doctorDataRepository;
  PatientDataRepository patientDataRepository;
  Print print;
  public RegisterService(DoctorDataRepository doctorDataRepository, PatientDataRepository patientDataRepository, Print print) {
    this.doctorDataRepository =doctorDataRepository;
    this.patientDataRepository =patientDataRepository;
    this.print = print;
  }

  public void registerDoctor(String username, Speciality speciality){


    try {
      doctorDataRepository.addDoctor(username,speciality);
    } catch (DoctorAlreadyRegisterd e) {
      print.printData("Doctor already exists");
      return;
    }
    print.printData("Doctor registerd");
  }

  public void registerPatient(String username){
    try {
      patientDataRepository.addPatient(username);
    } catch (PatientAlreadyRegistered e) {
      print.printData("Patient already registered");
      return;
    }
    print.printData("Patient registered");
  }
}
