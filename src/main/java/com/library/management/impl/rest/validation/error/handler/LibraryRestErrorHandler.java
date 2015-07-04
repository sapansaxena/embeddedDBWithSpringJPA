package com.library.management.impl.rest.validation.error.handler;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Maps;
import com.library.management.api.rest.exception.handler.LibraryException;

	@ControllerAdvice
	public class LibraryRestErrorHandler {
	 
	    private MessageSource messageSource;
	 
	    @Autowired
	    public LibraryRestErrorHandler(MessageSource messageSource) {
	        this.messageSource = messageSource;
	    }
	 
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    public @ResponseBody ValidationErrorDTO handleValidationException(MethodArgumentNotValidException ex) throws IOException {
	        //Map<String, Object>  map = Maps.newHashMap();
	        ValidationErrorDTO dto = new ValidationErrorDTO();
/*	        map.put("error", "Validation Failure");
	        map.put("violations", convertConstraintViolation(ex));*/
	        BindingResult result = ex.getBindingResult();
	        List<FieldError> fieldErrors = result.getFieldErrors();
	 
	        return processFieldErrors(fieldErrors);
	    }
	    @ExceptionHandler(LibraryException.class)
	    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	    public @ResponseBody Map<String, Object> handleInternalServerError(LibraryException ex) throws IOException {
	 /*       //Map<String, Object>  map = Maps.newHashMap();
	        ValidationErrorDTO dto = new ValidationErrorDTO();
	        map.put("error", "Validation Failure");
	        map.put("violations", convertConstraintViolation(ex));
	        BindingResult result = ex.getBindingResult();
	        List<FieldError> fieldErrors = result.getFieldErrors();*/
	    	Map<String, Object>  map = Maps.newHashMap();
	    	map.put("error", "Library Management System Exception");
	    	map.put("message", ex.getMessage());
	    	map.put("detailMessage", ex.getErrorMessage());
	        return map;
	    }
	    @ExceptionHandler(HibernateException.class)
	    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	    public @ResponseBody Map<String, Object> handleInternalServerError(Exception ex) throws IOException {
	 /*       //Map<String, Object>  map = Maps.newHashMap();
	        ValidationErrorDTO dto = new ValidationErrorDTO();
	        map.put("error", "Validation Failure");
	        map.put("violations", convertConstraintViolation(ex));
	        BindingResult result = ex.getBindingResult();
	        List<FieldError> fieldErrors = result.getFieldErrors();*/
	    	Map<String, Object>  map = Maps.newHashMap();
	    	map.put("error", "Cannot perform execution");
	    	map.put("message", ex.getMessage());
	    	map.put("detailMessage", ex.getLocalizedMessage());
	        return map;
	    }
	    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
	        ValidationErrorDTO dto = new ValidationErrorDTO();
	 
	        for (FieldError fieldError: fieldErrors) {
	            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
	            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
	        }
	 
	        return dto;
	    }
	 
	    private String resolveLocalizedErrorMessage(FieldError fieldError) {
	        Locale currentLocale =  LocaleContextHolder.getLocale();
	        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
	 
	        //If the message was not found, return the most accurate field error code instead.
	        //You can remove this check if you prefer to get the default error message.
	        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
	            String[] fieldErrorCodes = fieldError.getCodes();
	            if(fieldErrorCodes != null)
	            localizedErrorMessage = fieldErrorCodes[0];
	        }
	 
	        return localizedErrorMessage;
	    }
	   /* private Map<String, Map<String, Object> > convertConstraintViolation(MethodArgumentNotValidException ex) {
	        Map<String, Map<String, Object>> result = Maps.newHashMap();
	        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
	            Map<String, Object>  violationMap = Maps.newHashMap();
	            violationMap.put("target", ex.getBindingResult().getTarget());
	            violationMap.put("type", ex.getBindingResult().getTarget().getClass());
	            violationMap.put("message", error.getDefaultMessage());
	            result.put(error.getObjectName(), violationMap);
	        }
	        return result;
	    }	*/
	    }
