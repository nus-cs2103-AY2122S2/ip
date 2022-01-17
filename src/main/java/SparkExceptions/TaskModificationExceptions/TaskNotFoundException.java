package SparkExceptions.TaskModificationExceptions;

import SparkExceptions.SparkException;

public class TaskNotFoundException extends SparkException {
    public TaskNotFoundException() {
        super("I couldn't find the Task that you wanted :(");
    }
}
