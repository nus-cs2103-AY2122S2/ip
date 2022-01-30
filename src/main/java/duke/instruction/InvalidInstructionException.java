package main.java.duke.instruction;

import main.java.duke.main.DukeException;

/**
 * Represents the exception that occurs when the user input of command is invalid.
 */
public class InvalidInstructionException extends DukeException {

    /**
     * Constructors the exception with the specified message.
     *
     * @param message The message to be shown when the exception is thrown.
     */
    protected InvalidInstructionException(String message) {
        super(message);
    }
}
