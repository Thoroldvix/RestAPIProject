package com.example.RESTAPIProject.util;

import com.example.RESTAPIProject.dto.MeasurementDTO;
import com.example.RESTAPIProject.models.Sensor;
import com.example.RESTAPIProject.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasurementDTOValidator implements Validator {


    private final SensorService sensorService;

    public MeasurementDTOValidator(SensorService sensorService) {

        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        Optional<Sensor> checkedSensor = sensorService.findByName(measurementDTO.getSensor().getName());
        if (checkedSensor.isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with this name doesn't exists");
        }

    }
}
