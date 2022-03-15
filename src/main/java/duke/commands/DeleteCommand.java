package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;

/**
 * Represents a <code>DeleteCommand</code> which is called when the program deletes a Task.
 */
public class DeleteCommand implements Command {

    private int index;

    /**
     * Constructor for Delete Command which takes in the index to delete.
     * @param input
     */
    public DeleteCommand(String input) throws DukeException {
        String[] splitted = input.split(" ");
        if (splitted.length != 2) {
            throw new DukeException("Invalid format for delete. \n" +
                    "Correct format: delete <INDEX>\n" +
                    "Example: delete 3"
            );
        }
        if (!splitted[1].matches("\\d+$")) {
            throw new DukeException("Invalid format for delete. \n" +
                    "Correct format: delete <INDEX>\n" +
                    "Example: delete 3"
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
        Task removedTask = tasks.deleteTask(index - 1);
        response += "Noted. I've removed this task: \n";
        response += removedTask + "\n";
        response += "Now you have " + tasks.getSize() + " tasks in the list.";
        return response;
    }
}
