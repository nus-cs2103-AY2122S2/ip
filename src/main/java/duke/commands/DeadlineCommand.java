package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;

/**
 * Represents a <code>DeadlineCommand</code> which is called when the program adds a Deadline.
 */
public class DeadlineCommand implements Command {

    private Deadline toAdd;

    /**
     * Constructor for a DeadlineCommand which takes in the relevent Deadline.
     * @param toAdd
     */
    public DeadlineCommand(Deadline toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        if (tasks.containsTask(toAdd.getName())) {
            throw new DuplicateException(toAdd.getName());
        }
        tasks.addTask(toAdd);
        response += "Got it. I've added this task: \n";
        response += toAdd + "\n";
        response += "Now you have " + tasks.getSize() + " tasks in the list.";
        return response;
    }
}
