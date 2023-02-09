package com.example.RESTAPIProject.controllers;

import com.example.RESTAPIProject.dto.MeasurementDTO;
import com.example.RESTAPIProject.errors.MeasurementErrorResponse;
import com.example.RESTAPIProject.errors.MeasurementNotCreatedException;
import com.example.RESTAPIProject.models.Measurement;
import com.example.RESTAPIProject.service.MeasurementService;
import com.example.RESTAPIProject.util.MeasurementDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.RESTAPIProject.util.Util.getErrorMessages;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementDTOValidator measurementDTOValidator;

    private final ModelMapper modelMapper;

    private final MeasurementService measurementService;


    @Autowired
    public MeasurementController(MeasurementDTOValidator measurementDTOValidator, ModelMapper modelMapper, MeasurementService measurementService) {
        this.measurementDTOValidator = measurementDTOValidator;
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;

    }
    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public String getRainyDaysCount() {
        return "Total count of rainy days is: " + measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult) {
        measurementDTOValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MeasurementNotCreatedException(getErrorMessages(bindingResult));
        }
        measurementService.create(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
