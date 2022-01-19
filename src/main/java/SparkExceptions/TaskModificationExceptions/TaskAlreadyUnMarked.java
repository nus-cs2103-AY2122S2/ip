package SparkExceptions.TaskModificationExceptions;

import SparkExceptions.SparkException;
import Tasks.Task;

public class TaskAlreadyUnMarked extends SparkException {
    public TaskAlreadyUnMarked(Task t) {
        super(String.format("   %s\nis already unmarked!", t));
    }
}