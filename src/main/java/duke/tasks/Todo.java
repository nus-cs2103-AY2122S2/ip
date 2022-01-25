package duke.tasks;

import duke.exceptions.DukeException;

public class Todo extends Task {
    public Todo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    protected static Todo createTask(String[] tokens) throws DukeException {
        String item = "";
        if (tokens.length == 1)
            throw new DukeException("The description of a todo task cannot be empty!");

        for (String token : tokens) {
            if (token.equals("todo")) {
                continue;
            } else {
                item += token + " ";
            }
        }
        return new Todo(item.trim());
    }
}
