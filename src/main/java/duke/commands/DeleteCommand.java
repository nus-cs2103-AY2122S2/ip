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
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
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
