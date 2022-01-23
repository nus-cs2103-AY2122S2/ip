package SparkExceptions.FileExceptions;

import SparkExceptions.SparkException;

public class TaskDecodingException extends SparkException {
    public TaskDecodingException() {
        super("Could not decode a Task from the TaskFile! (was it corrupted?)");
    }
}
