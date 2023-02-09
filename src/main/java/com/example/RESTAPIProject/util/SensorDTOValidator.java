package com.example.RESTAPIProject.util;

import com.example.RESTAPIProject.dto.SensorDTO;
import com.example.RESTAPIProject.models.Sensor;
import com.example.RESTAPIProject.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorDTOValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        Optional<Sensor> checkedSensor = sensorService.findByName(sensorDTO.getName());
         if (checkedSensor.isPresent()) {
            errors.rejectValue("name", "", "Sensor with this name already exists");
        }
    }
}
