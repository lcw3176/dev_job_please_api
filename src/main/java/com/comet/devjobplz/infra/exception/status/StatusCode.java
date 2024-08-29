package com.comet.devjobplz.infra.exception.status;

import org.springframework.http.HttpStatus;

public interface StatusCode {
    HttpStatus getHttpStatus();

    String getMessage();
}
