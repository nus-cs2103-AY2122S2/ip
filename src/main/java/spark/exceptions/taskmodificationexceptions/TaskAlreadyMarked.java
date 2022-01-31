package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;
import spark.tasks.tasktypes.Task;

/**
 * This is an exception thrown when the user attempts
 * to mark a completed Task as complete.
 */
public class TaskAlreadyMarked extends SparkException {
    public TaskAlreadyMarked(Task t) {
        super(String.format("   %s\nis already marked!", t));
    }
}
