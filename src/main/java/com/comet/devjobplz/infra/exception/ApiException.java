package com.comet.devjobplz.infra.exception;

import com.comet.devjobplz.infra.exception.status.StatusCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final StatusCode code;

    public ApiException(StatusCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ApiException(StatusCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
