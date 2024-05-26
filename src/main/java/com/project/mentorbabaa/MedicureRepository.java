package com.project.mentorbabaa;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MedicureRepository extends CrudRepository<Doctor, String> {
    List<Doctor> findByDoctorNameIgnoreCase(String doctorName);
}
