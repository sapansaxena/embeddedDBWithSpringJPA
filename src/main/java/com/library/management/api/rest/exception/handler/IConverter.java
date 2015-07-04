package com.library.management.api.rest.exception.handler;


public interface IConverter {
   Object convert(Object src, Object type);

   Object convert(Object[] srcs, Object type);

}
