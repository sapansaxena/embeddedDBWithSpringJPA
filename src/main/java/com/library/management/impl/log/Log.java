package com.library.management.impl.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public final class Log{
	  /**
	   * System-level diagnostic, always displayed in log.
	   */
	  public final static int FATAL = 0;

	  /**
	   * Error-level diagnostic.
	   */
	  public final static int ERROR = 1;

	  /**
	   * Warning-level diagnostic.
	   */
	  public final static int WARNING = 2;

	  /**
	   * Informational diagnostic.
	   */
	  public final static int INFO = 3;

	  /**
	   * Debugging diagnostic.
	   */
	  public final static int DEBUG = 4;

	  /**
	   * Maps the WFC error levels to <b>log4j</b> priorities.
	   */
	  private final static Level[] priorities = {
	          Level.FATAL,
	          Level.ERROR,
	          Level.WARN,
	          Level.INFO,
	          Level.DEBUG
	      };
	private static final Logger logger = Logger.getLogger(Log.class);

	public static void log(int priority, Object msg1, Throwable t){
		if(t!= null){
			logger.log(intToPriority(priority), expand(msg1, null, null), t);

		}else{
			logger.log(intToPriority(priority), expand(msg1, null, null));
		}
	}
	public static void log(int priority, Object msg1){
		log(priority, msg1, null);
	}
	
	public static void log(int priority, String[] messages){
		Object msg = messages;
		    log(priority, msg);	
	}
	  /**
	   * Log an exception and a message.
	   * The context defaults to the <b>WFC</b>. The level defaults to <b>ERROR</b>.
	   *
	   * @param exception An exception to log.
	   * @param message the string to log
	   */
	  public static void log(Throwable t, Object message) {
	    log(ERROR, message, t);
	  }
	  public static void log(Throwable t, String[] message) {
		Object msg = message;
	    log(ERROR, msg, t);
	  }
	
	  public static void log(int priority, String[] message, Throwable t) {
		Object msg = message;
	    log(priority, msg, t);
	  }
	/**
   * Gets the underlying <b>log4j</b> priority.
   * @param level <b>log4j</b> logging level.
   * @return <b>log4j</b> priority.
   */
  protected static int priorityToInt(Level level) {
    for (int i = 0; i < priorities.length; i++) {
      if (priorities[i].equals(level)) {
        return i;
      }
    }
    return 0;
  }
  /**
   * Convert a text version of the WFC log level to its integer value.
   *
   * @param level a string representing the WFC log level.
   * @return WFC log level.
   */
  public static int priorityStringToInt(String level) {
    if (level.equals("FATAL")) {
      return FATAL;
    }
    if (level.equals("ERROR")) {
      return ERROR;
    }
    if (level.equals("WARNING")) {
      return WARNING;
    }
    if (level.equals("WARN")) {
      return WARNING;
    }
    if (level.equals("INFO")) {
      return INFO;
    }
    if (level.equals("DEBUG")) {
      return DEBUG;
    }
    return DEBUG;
  }

  /**
   * Convert an integer value to the text representation of the WFC log level.
   *
   * @param level WFC log level.
   * @return WFC log level as text.
   */
  public static String priorityIntToString(int level) {
    switch (level) {
      case FATAL:
        return "FATAL";
      case ERROR:
        return "ERROR";
      case WARNING:
        return "WARNING";
      case INFO:
        return "INFO";
      default:
        return "DEBUG";
    }
  }
	/**
	   * Convert a WFC log level to a <b>log4j</b> priority .
	   * @param level WFC log level.
	   * @return <b>log4j</b> priority.
	   */
	  public static Level intToPriority(int level) {
	    if (level < FATAL) {
	      level = FATAL;
	    }
	    if (level > DEBUG) {
	      level = DEBUG;
	    }
	    return priorities[level];
	  }
	 /**
	   * Expands an object into loggable format. Handles throwables, arrays, and collections.
	   * The method will use recursion to create the string.
	   * @param object the object to expand.
	   * @param buffer A string buffer.
	   * @param list List of objects.
	   * @return An expanded version of the object.
	   */
	  protected static String expand(Object object, StringBuffer buffer, LinkedList list) {
	    if (buffer == null) {
	      buffer = new StringBuffer(1000);
	    }

	    // handle the various cases of objects to be logged
	    if (object == null) {
	      return buffer.toString();
	    }

	    if (object.getClass().isArray() || object instanceof Collection || object instanceof Enumeration) {
	      if (list == null) {
	        list = new LinkedList();
	        list.add(object);
	      }
	      else if (list.contains(object) == false) {
	        list.add(object);
	      }
	      else {
	        return buffer.toString();
	      }
	    }

	    if (object instanceof Throwable) {
	      Throwable t = (Throwable) object;
	      ByteArrayOutputStream stackTraceStream = new ByteArrayOutputStream(1000);
	      PrintWriter stackTraceWriter = new PrintWriter(stackTraceStream, true);
	      stackTraceStream.reset();
	      t.printStackTrace(stackTraceWriter);
	      if (stackTraceStream.size() > 0) {
	        buffer.append(stackTraceStream.toString());
	        buffer.append('\n');
	      }
	    }
	    else if (object.getClass().isArray()) {
	      Object[] o = (Object[]) object;
	      for (int i = 0; i < o.length; i++) {
	        expand(o[i], buffer, list);
	      }
	    }
	    else if (object instanceof Collection) {
	      Collection c = (Collection) object;
	      for (Iterator i = c.iterator(); i.hasNext(); ) {
	        expand(i.next(), buffer, list);
	      }
	    }
	    /*** Enumeration doesn't work well cause hasMoreElements leaves us at the end of the enumeration
	     with no way to back to the beginning. This screws up anyone else who tries to use the
	     enumerator after we're done with it
	     ****/
	    else if (object instanceof Enumeration) {
	      buffer.append("{");
	      Enumeration e = (Enumeration) object;
	      while (e.hasMoreElements()) {
	        expand(e.nextElement(), buffer, list);
	      }
	      buffer.append("}");
	    }
	    else {
	      buffer.append(object.toString());
	      buffer.append(' ');
	    }
	    return buffer.toString();
	  }
	  /**
	   * Determines if a given logging level is over <b>WFC's</b> threshold.
	   *
	   * @param threshold Current log level.
	   * @return <b>true</b> if the given level is at least as severe as the root category's threshold.
	   */
	  public static boolean priorityEnabled(int threshold) {
	    return (INFO >= threshold);
	  }
	 }
