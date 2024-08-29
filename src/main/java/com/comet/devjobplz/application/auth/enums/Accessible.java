package com.comet.devjobplz.application.auth.enums;

import com.comet.devjobplz.application.auth.AdminValidation;
import com.comet.devjobplz.application.auth.ServerValidation;
import com.comet.devjobplz.application.auth.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Accessible {

    FRIENDLY_SERVER(ServerValidation.class),
    ADMIN(AdminValidation.class),
    ;


    private final Class<? extends Validation> validationClass;
}
