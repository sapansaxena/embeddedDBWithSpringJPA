package com.library.management.impl.framework.exception;

/**
 * Thrown when a Resource is requested by id and not found
 *
 * Created by michael.clark on 6/25/14.
 */
class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){super(message);}
    public ResourceNotFoundException(String message, Throwable t){super(message,t);}

}
