package com.example.RESTAPIProject.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTO {
    private LocalDateTime timeOfMeasurement;
    private int rainyDaysCount;
    @Range(min = -100, max = 100, message = "Value should be between -100 and 100")
    private float value;

    @NotNull(message = "Raining should not be empty")
    private boolean raining;
    private SensorDTO sensor;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(LocalDateTime timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public int getRainyDaysCount() {
        return rainyDaysCount;
    }

    public void setRainyDaysCount(int rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }


}
