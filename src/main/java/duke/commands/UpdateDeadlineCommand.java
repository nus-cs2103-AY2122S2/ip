package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;

import java.time.format.DateTimeParseException;


/**
 * Represents a <code>UpdateDeadlineCommand</code> which is called to update a Deadline.
 */

public class UpdateDeadlineCommand implements Command {
    private int index;
    private String deadline;

    /**
     * Constructor which takes in the relevant index and deadline.
     * @param index
     * @param deadline
     */
    public UpdateDeadlineCommand(int index, String deadline) {
        this.index = index;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (index <= 0 || index > tasks.getSize()) {
            throw new IndexException();
        }
        Task oldTask = tasks.get(index - 1);
        if (oldTask instanceof Deadline) {
            Deadline oldDeadlineTask = (Deadline) oldTask;
            try {
                tasks.set(index - 1, new Deadline(oldDeadlineTask.getName(), deadline));
                return "Successfully altered the deadline. The new Deadline task at index " + (index) + "\n"
                        + "is " +  tasks.get(index - 1);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid format for update deadline. \n" +
                        "Correct format: update deadline <index> <YYYY-MM-DD> <HHMM>\n" +
                        "Example: update deadline 3 2012-04-03 1500\n changes the deadline of the Task at index 3" +
                        " to 3rd April 2012 3PM.");
            }

        } else {
            throw new DukeException("The indicated task is not of type Deadline.");
        }
    }
}
