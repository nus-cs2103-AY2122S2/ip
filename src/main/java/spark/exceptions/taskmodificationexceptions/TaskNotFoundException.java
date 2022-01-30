package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;

public class TaskNotFoundException extends SparkException {
    public TaskNotFoundException() {
        super("Wait a minute... that Task does not seem to exist");
    }
}
