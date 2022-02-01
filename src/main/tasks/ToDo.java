package main.tasks;

import main.enums.TaskType;

public class ToDo extends Task{
    public ToDo(String description) {
       super(description, TaskType.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }
}
