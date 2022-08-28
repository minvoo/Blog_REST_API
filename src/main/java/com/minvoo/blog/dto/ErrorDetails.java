package com.minvoo.blog.dto;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Accessors(chain = true)
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


}
