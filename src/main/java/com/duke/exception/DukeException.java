package com.duke.exception;

/**
 * Exception thrown by Duke.
 */
public class DukeException extends Exception{

    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Return the error message thrown by Duke
     * @return Error message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
