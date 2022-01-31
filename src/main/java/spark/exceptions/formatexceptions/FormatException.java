package spark.exceptions.formatexceptions;

import spark.exceptions.SparkException;

/**
 * This is a class of exceptions thrown when the user
 * gives an invalid command to Spark.
 */
public class FormatException extends SparkException {
    public FormatException(String errorMessage) {
        super(errorMessage);
    }
}