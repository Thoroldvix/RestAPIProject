package com.example.RESTAPIProject.errors;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String message) {
        super(message);
    }
}
