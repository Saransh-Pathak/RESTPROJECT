package com.scaler.demo123.dto;

import lombok.Getter;
import lombok.Setter;


public class ErrorDto {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
