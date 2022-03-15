package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;

/**
 * Represents a <code>MarkCommand</code> which is called to mark a task as done..
 */
public class MarkCommand implements Command {

    private int index;

    /**
     * Constructor for Mark Command, takes the index to mark as done.
     * @param input
     */
    public MarkCommand(String input) throws DukeException {
        String[] splitted = input.split(" ");
        if (splitted.length != 2) {
            throw new DukeException("Invalid format for mark. \n" +
                    "Correct format: mark <INDEX>\n" +
                    "Example: mark 3"
            );
        }
        if (!splitted[1].matches("\\d+$")) {
            throw new DukeException("Invalid format for mark. \n" +
                    "Correct format: mark <INDEX>\n" +
                    "Example: mark 3"
            );
        }
        this.index = Integer.valueOf(splitted[1]);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        if (index <= 0 || index > tasks.getSize()) {
            throw new IndexException();
        }
        Task set = tasks.setDone(index - 1);
        response += "Nice! I've marked this task as done: \n" + set;
        return response;
    }
}
