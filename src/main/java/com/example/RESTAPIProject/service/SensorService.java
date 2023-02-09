package com.example.RESTAPIProject.service;

import com.example.RESTAPIProject.dto.SensorDTO;
import com.example.RESTAPIProject.models.Sensor;
import com.example.RESTAPIProject.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {


    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findSensorByName(name);
    }



    @Transactional
    public void create(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
