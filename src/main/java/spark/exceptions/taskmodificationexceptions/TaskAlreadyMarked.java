package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;
import spark.tasks.tasktypes.Task;

public class TaskAlreadyMarked extends SparkException {
    public TaskAlreadyMarked(Task t) {
        super(String.format("   %s\nis already marked!", t));
    }
}
