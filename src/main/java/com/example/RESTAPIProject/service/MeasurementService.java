package com.example.RESTAPIProject.service;

import com.example.RESTAPIProject.models.Measurement;
import com.example.RESTAPIProject.models.Sensor;
import com.example.RESTAPIProject.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public void create(Measurement measurement) {
        setMeasurementSensor(measurement);
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setTimeOfMeasurement(LocalDateTime.now());
    }

    private void setMeasurementSensor(Measurement measurement) {
        Optional<Sensor> sensor = sensorService.findByName(measurement.getSensor().getName());
        sensor.ifPresent(measurement::setSensor);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int getRainyDaysCount() {
       return measurementRepository.getRainyDaysCount();
    }
}
