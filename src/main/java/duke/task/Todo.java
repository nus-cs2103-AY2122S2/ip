package duke.task;

import duke.DukeException;


/**
 * Todo represents a Task that a user must complete, but with no deadline.
 *
 * @author Jian Rong
 */
public class Todo extends Task {
    private final String title;


    /**
     * Constructor for Todo Class.
     *
     * @param command Input given from Duke
     * @throws DukeException  If syntax of command is wrong
     */
    public Todo(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("Please use this format: duke.task.Todo <Activity>");
        }
        this.title = command;
        System.out.println("added: " + this.toString());
    }

    /**
     * Returns a summary of the Todo Task.
     * @return The summary of the Todo.
     */
    public String toString(){
        if (this.checked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

}
