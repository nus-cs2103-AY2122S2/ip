package spark.exceptions.formatexceptions;

import spark.exceptions.SparkException;

public class FormatException extends SparkException {
    public FormatException(String errorMessage) {
        super(errorMessage);
    }
}