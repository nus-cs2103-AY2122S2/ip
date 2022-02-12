package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;
import spark.tasks.tasktypes.Task;

/**
 * This is an exception thrown when the user attempts
 * to mark an incomplete Task as incomplete.
 */
public class TaskAlreadyUnMarked extends SparkException {
    public TaskAlreadyUnMarked(Task t) {
        super(String.format("   %s\nis already unmarked!", t));
    }
}
