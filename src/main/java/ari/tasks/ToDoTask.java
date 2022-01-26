package main.java.ari.tasks;

import main.java.ari.exception.EmptyCommandException;

public class ToDoTask extends Task {
    public ToDoTask(String message) throws EmptyCommandException {
        super(message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "todo "+ super.writeToFile();
    }
}
