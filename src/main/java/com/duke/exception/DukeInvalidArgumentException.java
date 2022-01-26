package com.duke.exception;

/**
 * Represents a exception when encountering invalid argument structure for Duke Commands.
 */
public class DukeInvalidArgumentException extends DukeException{
  public DukeInvalidArgumentException(String errormessage) {
    super(errormessage);
  }
}
