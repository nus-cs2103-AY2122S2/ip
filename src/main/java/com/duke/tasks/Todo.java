package com.duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveDescription() {
        return String.format("%s | %s | %s ",
                getClass().getName(), status == true ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
