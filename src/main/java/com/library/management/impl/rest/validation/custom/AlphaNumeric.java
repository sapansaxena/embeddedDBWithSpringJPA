package com.library.management.impl.rest.validation.custom;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlphaNumericValidator.class)
@Documented
//@Pattern(regexp = "^[A-Za-z0-9]+$", message = "{error.validation.not_alphanumeric}")
public @interface AlphaNumeric {
	String message() default "{Phone}";
	Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
