package com.example.RESTAPIProject.repository;

import com.example.RESTAPIProject.models.Measurement;
import com.example.RESTAPIProject.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query("select count(*) from Measurement where raining = true")
    int getRainyDaysCount();
}
