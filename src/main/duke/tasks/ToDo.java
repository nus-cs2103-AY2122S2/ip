package main.duke.tasks;

import main.duke.enums.TaskType;

public class ToDo extends Task{
    public ToDo(String description) {
       super(description, TaskType.TODO);
    }

    public Task clone() {
        return new ToDo(this.getDescription(), this.getIsDone());
    }

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }
}
