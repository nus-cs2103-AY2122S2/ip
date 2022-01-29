package task;

import task.Task;

public class Todo extends Task {

    public Todo(String details) {
        super(details);
    }

    public String symbol() {
        return "T";
    }

    @Override
    public String displayTime() {
        return super.toString();
    }

}
