package com.duke.exception;

/**
 * Represents a exception when trying to perform operations on an empty TaskList.
 */
public class DukeEmptyListException extends DukeException {
  public DukeEmptyListException(String errormessage) {
    super(errormessage);
  }
}
