package com.gen_4.horse_market.exceptions;

public class DuplicatedEntityException extends RuntimeException {
  
    public DuplicatedEntityException(String message) {
        super(message);
    }

    public DuplicatedEntityException(String message, Throwable error) {
        super(message, error);
    }

}
