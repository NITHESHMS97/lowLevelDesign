package org.nithesh.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.nithesh.Enum.Speciality;
import org.nithesh.Enum.Status;
import org.nithesh.exceptions.ConflictInBooking;
import org.nithesh.exceptions.DoctorNotAvailable;
import org.nithesh.model.Availabiltiy;
import org.nithesh.model.Booking;
import org.nithesh.model.TimeSlot;

public class AppointmentRepository {
  HashMap<Speciality, ArrayList<Availabiltiy>> availableSlotsBySpeciality;
  HashMap<String ,ArrayList<Availabiltiy>> availableSlots;
  HashMap<String , ArrayList<Booking>>  patientBookings;
  HashMap<String, ArrayList<Booking>> bookingsForDoctor;
  HashMap<String, ArrayList<Booking>> waitlistForDoctor;

  Integer bookingIdGenerator;

  public AppointmentRepository(){
    availableSlots = new HashMap<>();
    availableSlotsBySpeciality = new HashMap<>();
    patientBookings = new HashMap<>();
    bookingsForDoctor = new HashMap<>();
    waitlistForDoctor = new HashMap<>();
    bookingIdGenerator=0;
  }

  public Integer getBookinId(){
    bookingIdGenerator++;
    return bookingIdGenerator;
  }
  public void addDoctorAvailability(String doctor, TimeSlot timeSlot, Speciality speciality){
    String id = doctor + timeSlot.toString();
    Availabiltiy availabiltiy = new Availabiltiy(id,timeSlot, doctor,Status.FREE);
      availableSlots.computeIfAbsent(id,k->new ArrayList<>()).add(availabiltiy);
      availableSlotsBySpeciality.computeIfAbsent(speciality,k->new ArrayList<>()).add(availabiltiy);
  }

  public List<Availabiltiy> searchBySepiality(Speciality speciality){
    return availableSlotsBySpeciality.get(speciality).stream().filter(availabiltiy ->  availabiltiy.getStatus() == Status.FREE).collect(
        Collectors.toList());
  }

  public Integer bookAppointment(String doctor, TimeSlot timeSlot, String patient)
      throws DoctorNotAvailable, ConflictInBooking {

    String avaialblityId = doctor+timeSlot.toString();
    if(!availableSlots.containsKey(avaialblityId))
      throw new DoctorNotAvailable();
    Integer id = getBookinId();
    Booking booking = new Booking(id, doctor,timeSlot,patient);
    for(Booking patientBooking : patientBookings.computeIfAbsent(patient,k->new ArrayList<>())){
      if(patientBooking.getTimeSlot().getStartTime() == timeSlot.getStartTime())
        throw new ConflictInBooking();
    }
    bookingsForDoctor.computeIfAbsent(doctor, k-> new ArrayList<>()).add(booking);
    patientBookings.computeIfAbsent(patient,k->new ArrayList<>()).add(booking);
    return id;
  }

  public List<Booking> getPatientBooking(String patient){
    return patientBookings.computeIfAbsent(patient,k->new ArrayList<>());
  }

  public List<Booking> getDoctorBooking(String doctor){
    return bookingsForDoctor.computeIfAbsent(doctor,k->new ArrayList<>());
  }
}
