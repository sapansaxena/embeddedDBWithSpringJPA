package com.library.management.api.rest.exception.handler;

import com.library.management.api.exception.handler.PublicException;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;

public class LibraryException extends PublicException{
	private static final long serialVersionUID = 4899718906287352188L;
	   
	   private static IConverter converter;
	   
	   public LibraryException() {
	      super();
	   }

	   public LibraryException(String message, Throwable ex) {
	      super(message, ex);
	   }

	   public LibraryException(String message) {
	      super(message);
	   }

		protected static IConverter getConverter() {
			if (converter == null) {
				converter = new LibraryExceptionConverter();
			}
			return converter;
		}
		
		public static LibraryException unify(Throwable e) {
		   if(e instanceof PublicException) {
		      throw (PublicException) e;
		   } else {
		      try {
		         return (LibraryException) getConverter().convert( e, new LibraryException());
		      } catch (Exception converterException) {
		  		Log.log(Logger.ERROR, "Cannot convert");
		         throw converterException;
		      }
		   }
		}


}
