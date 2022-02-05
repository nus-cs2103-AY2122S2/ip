package duke.task;

import duke.DukeException;

public class Todo extends Task {
    private final String title;

    public Todo(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("Please use this format: duke.task.Todo <Activity>");
        }
        this.title = command;
        System.out.println("added: " + this.toString());
    }

    public String toString() {
        if (this.isChecked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

}
