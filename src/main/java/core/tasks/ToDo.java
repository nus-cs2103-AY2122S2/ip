package core.tasks;

import core.exceptions.ToDoEmptyException;
import utilities.OutputFormatter;

public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    public static ToDo getInstance(String description) throws ToDoEmptyException {
        if (description.isEmpty() || description.isBlank()) {
            throw new ToDoEmptyException();
        }
        return new ToDo(description);
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[T]", super.toString());
        return outputFormatter.getFormattedOutput();
    }
}
