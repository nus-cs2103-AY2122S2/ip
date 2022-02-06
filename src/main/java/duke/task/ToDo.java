package duke.task;

import duke.utils.Priority;

public class ToDo extends Task {
    public ToDo(String description, Priority priority) {
        super(description, TaskType.TODO, priority);
    }
}
