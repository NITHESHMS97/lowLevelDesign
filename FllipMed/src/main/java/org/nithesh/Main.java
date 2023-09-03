package org.nithesh;

import java.time.LocalTime;
import org.nithesh.Enum.Speciality;
import org.nithesh.mode.ConsolePrint;
import org.nithesh.mode.Print;
import org.nithesh.model.TimeSlot;
import org.nithesh.repository.AppointmentRepository;
import org.nithesh.repository.DoctorDataRepository;
import org.nithesh.repository.PatientDataRepository;
import org.nithesh.service.BookingService;
import org.nithesh.service.RegisterService;

public class Main {

  public static void main(String[] args) {
    DoctorDataRepository doctorDataRepository = new DoctorDataRepository();
    PatientDataRepository patientDataRepository = new PatientDataRepository();
    AppointmentRepository appointmentRepository = new AppointmentRepository();
    Print print = new ConsolePrint();
    RegisterService registerService = new RegisterService(doctorDataRepository,patientDataRepository,print);
    BookingService bookingService = new BookingService(appointmentRepository,doctorDataRepository,print);

    registerService.registerDoctor("doom", Speciality.Dermatologist);
    registerService.registerPatient("boom");

    bookingService.addAvaialabilty("doom", new TimeSlot(LocalTime.of(9,30),LocalTime.of(10,00) ));
    bookingService.addAvaialabilty("doom", new TimeSlot(LocalTime.of(10,30),LocalTime.of(11,00) ));
    bookingService.addAvaialabilty("doom", new TimeSlot(LocalTime.of(11,30),LocalTime.of(12,00) ));

    bookingService.searchDoctors(Speciality.Dermatologist);
    bookingService.bookDoctor("doom",new TimeSlot(LocalTime.of(10,30),LocalTime.of(11,00)), "boom");

    bookingService.getPatientBookings("boom");
    bookingService.getDoctorBookings("doom");
  }
}