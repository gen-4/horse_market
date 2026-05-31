package com.gen_4.horse_market.exceptions;

public class ParametersValidationException extends RuntimeException {
  
    public ParametersValidationException(String message) {
        super(message);
    }

    public ParametersValidationException(String message, Throwable error) {
        super(message, error);
    }

}
