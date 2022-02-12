package spark.exceptions.fileexceptions;

import spark.exceptions.SparkException;

/**
 * This is an exception that is thrown when a user's save-file contains
 * an encoded object that could not be decoded, possibly due to the
 * user making some unintended changes to it or corruption.
 */
public class TaskDecodingException extends SparkException {
    public TaskDecodingException() {
        super("Could not decode a Task from the TaskFile! (was it corrupted?)");
    }
}
