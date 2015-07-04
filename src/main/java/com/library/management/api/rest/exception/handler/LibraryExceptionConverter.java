package com.library.management.api.rest.exception.handler;

import java.util.HashMap;
import java.util.Map;


public class LibraryExceptionConverter implements IConverter {

   private static LibraryExceptionConverter INSTANCE = null;

   public static synchronized LibraryExceptionConverter getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new LibraryExceptionConverter();
      }
      return INSTANCE;
   }

   public LibraryExceptionConverter() {
      super();
   }

   public Object convert( Object src, Object type)  {
	   Throwable source = (Throwable)src;
	   LibraryException libraryEx = (LibraryException)type;
	   libraryEx.setStackTrace(source.getStackTrace());
	   libraryEx.setSeverity(LibraryException.SEVERITY_ERROR);
	   libraryEx.setExceptionClass(LibraryException.class.getName());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("message", source.getMessage());
		libraryEx.setParameters(parameters);
		return libraryEx;
}

   public Object convert( Object[] srcs, Object type) {
	   return null;
     // return _convert(srcs, type);
//      return context == null ? _convert(srcs, type) : _convert(context, srcs, type);
   }



}
