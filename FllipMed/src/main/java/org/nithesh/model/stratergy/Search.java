package org.nithesh.model.stratergy;

import java.util.List;
import org.nithesh.Enum.Speciality;
import org.nithesh.model.Availabiltiy;

public interface Search {
  public List<Availabiltiy> searchDoctors(Speciality speciality);

}
