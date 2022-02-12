package spark.exceptions.formatexceptions;

import spark.exceptions.SparkException;

/**
 * This is a class of exceptions thrown when the user
 * gives an invalid command to Spark.
 */
public abstract class FormatException extends SparkException {
    /**
     * Creates an Exception containing the specified
     * error message to be displayed to the user on the GUI.
     */
    public FormatException(String errorMessage) {
        super(errorMessage);
    }
}
