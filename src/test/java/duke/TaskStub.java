package duke;

import duke.task.Task;

public class TaskStub extends Task{

    static final String STUB = "I AM A TASK_STUB";

    public TaskStub() {
        super(STUB);
    }

    @Override
    public String toFileFormat() {
        return STUB;
    }

    @Override
    public String toString() {
        return STUB;
    }
    
}
