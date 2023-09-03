package org.nithesh.model.stratergy;

import java.util.Comparator;
import java.util.List;
import org.nithesh.Enum.Speciality;
import org.nithesh.model.Availabiltiy;
import org.nithesh.repository.AppointmentRepository;

public class SearchByStartTime implements Search {

  AppointmentRepository appointmentRepository;
  public SearchByStartTime(AppointmentRepository appointmentRepository){
    this.appointmentRepository = appointmentRepository;
  }
  @Override
  public List<Availabiltiy> searchDoctors(Speciality speciality){
    List<Availabiltiy>  availabilties =  appointmentRepository.searchBySepiality(speciality);
    availabilties.sort(Comparator.comparing((Availabiltiy availability) -> availability.getTimeSlot().getStartTime()).reversed());
    return availabilties;
  }

}
