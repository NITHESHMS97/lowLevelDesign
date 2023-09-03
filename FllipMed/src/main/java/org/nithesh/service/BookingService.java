package org.nithesh.service;

import java.util.ArrayList;
import java.util.List;
import org.nithesh.Enum.Speciality;
import org.nithesh.exceptions.ConflictInBooking;
import org.nithesh.exceptions.DoctorNotAvailable;
import org.nithesh.exceptions.DoctorNotFound;
import org.nithesh.mode.Print;
import org.nithesh.model.Availabiltiy;
import org.nithesh.model.Booking;
import org.nithesh.model.TimeSlot;
import org.nithesh.model.stratergy.SearchByStartTime;
import org.nithesh.repository.AppointmentRepository;
import org.nithesh.repository.DoctorDataRepository;

public class BookingService {

  AppointmentRepository appointmentRepository;
  DoctorDataRepository doctorDataRepository;
  Print print;
  public BookingService(AppointmentRepository appointmentRepository, DoctorDataRepository doctorDataRepository, Print print) {
    this.appointmentRepository = appointmentRepository;
    this.doctorDataRepository = doctorDataRepository;
    this.print = print;
  }

  public void addAvaialabilty(String doctor , TimeSlot timeSlot)
  {
    Speciality speciality = null;
    try {
      speciality = doctorDataRepository.getDoctorById(doctor).getSpeciality();
    } catch (DoctorNotFound e) {
      print.printData("Doctor not found");
    }
    appointmentRepository.addDoctorAvailability(doctor, timeSlot,speciality);
    print.printData("avaiability added");
  }
  public void searchDoctors(Speciality speciality){
    List<Availabiltiy>  availabilties = new SearchByStartTime(appointmentRepository).searchDoctors(speciality);
    if(availabilties.size() == 0){
      print.printData("no doctors available");
      return;
    }
    for(Availabiltiy availabiltiy: availabilties){
      print.printData(availabiltiy.getDoctor() + " " + availabiltiy.getTimeSlot().getStartTime()+" " + availabiltiy.getTimeSlot().getEndTime()+ " "+ availabiltiy.getStatus());
    }
    print.printData("---------------------------------");
  }

  public void bookDoctor(String doctor,TimeSlot timeSlot, String patient){
    Integer id;
    try {
      id = appointmentRepository.bookAppointment(doctor,timeSlot,patient);
    } catch (DoctorNotAvailable e) {
      print.printData("Doctor not found");
      return;
    } catch (ConflictInBooking e) {
      print.printData("Booking already present in same timeSlot");
      return;
    }
    print.printData("Booking done :"+ id);
  }

  public void getPatientBookings(String patient){
    List<Booking> bookings =  appointmentRepository.getPatientBooking(patient);
    for(Booking booking : bookings){
      print.printData(booking.getId() + " " + booking.getTimeSlot().getStartTime() + " " + booking.getTimeSlot().getStartTime() );
    }
    print.printData("-----------------------");
  }

  public void getDoctorBookings(String doctor){
    List<Booking> bookings =  appointmentRepository.getDoctorBooking(doctor);
    for(Booking booking : bookings){
      print.printData(booking.getId() + " " + booking.getTimeSlot().getStartTime() + " " + booking.getTimeSlot().getStartTime() );
    }
    print.printData("-----------------------");
  }
}
