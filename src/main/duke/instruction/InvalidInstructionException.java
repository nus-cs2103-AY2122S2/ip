package main.duke.instruction;

import main.duke.main.DukeException;

/**
 * Represents the exception that occurs when the user input of command is invalid.
 */
public class InvalidInstructionException extends DukeException {

    protected InvalidInstructionException(String message) {
        super(message);
    }
}
