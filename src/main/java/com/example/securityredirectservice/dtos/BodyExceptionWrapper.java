package com.example.securityredirectservice.dtos;

import lombok.Data;

@Data
public class BodyExceptionWrapper {
    private String status;
    private String reason;

    public BodyExceptionWrapper(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
}
