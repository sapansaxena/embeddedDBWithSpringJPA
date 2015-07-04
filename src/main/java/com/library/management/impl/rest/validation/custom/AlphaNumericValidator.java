package com.library.management.impl.rest.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumeric, String>{

	@Override
	public void initialize(AlphaNumeric constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		if(field == null){
			return false;
		}
		return field.matches("^[A-Za-z0-9\\s]+$");
	}
	

}
