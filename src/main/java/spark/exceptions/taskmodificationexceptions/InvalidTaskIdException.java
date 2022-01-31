package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;

/**
 * This is an exception thrown when a user fails to input
 * an integer that would identify the Task to be modified;
 * such as inputting an alphabet instead of an integer.
 */
public class InvalidTaskIdException extends SparkException {
    public InvalidTaskIdException(String invalidInput) {
        super(String.format("Your input:\n  %s\ndoes not seem like an integer. Task numbers are integers!", invalidInput));
    }
}
