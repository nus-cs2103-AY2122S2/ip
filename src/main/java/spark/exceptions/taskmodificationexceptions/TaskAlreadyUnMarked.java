package spark.exceptions.taskmodificationexceptions;

import spark.exceptions.SparkException;
import spark.tasks.tasktypes.Task;

public class TaskAlreadyUnMarked extends SparkException {
    public TaskAlreadyUnMarked(Task t) {
        super(String.format("   %s\nis already unmarked!", t));
    }
}