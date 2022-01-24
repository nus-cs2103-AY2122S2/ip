package spark.exceptions.fileexceptions;

import spark.exceptions.SparkException;

public class TaskDecodingException extends SparkException {
    public TaskDecodingException() {
        super("Could not decode a Task from the TaskFile! (was it corrupted?)");
    }
}
