package com.duke.exception;

/**
 * Represents a exception when encountering invalid save data structure in the saved listData file.
 */
public class DukeFileParseException extends DukeException {
    public DukeFileParseException(String errormessage) {
        super(errormessage);
    }
}
