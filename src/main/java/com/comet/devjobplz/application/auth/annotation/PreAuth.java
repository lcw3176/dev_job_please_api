package com.comet.devjobplz.application.auth.annotation;


import com.comet.devjobplz.application.auth.enums.Accessible;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuth {

    Accessible[] value();

}
