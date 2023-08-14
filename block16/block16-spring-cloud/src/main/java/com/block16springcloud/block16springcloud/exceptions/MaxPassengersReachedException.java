package com.block16springcloud.block16springcloud.exceptions;

public class MaxPassengersReachedException extends RuntimeException {
    public MaxPassengersReachedException(String message) {
        super(message);
    }
}