package SparkExceptions.TaskModificationExceptions;

import SparkExceptions.SparkException;
import Tasks.Task;

public class TaskAlreadyMarked extends SparkException {
    public TaskAlreadyMarked(Task t) {
        super(String.format("   %s\nis already marked!", t));
    }
}
