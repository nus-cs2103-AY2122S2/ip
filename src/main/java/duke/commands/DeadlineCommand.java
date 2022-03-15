package duke.commands;

import duke.exceptions.DeadlineException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;

import java.time.format.DateTimeParseException;

/**
 * Represents a <code>DeadlineCommand</code> which is called when the program adds a Deadline.
 */
public class DeadlineCommand implements Command {

    private Deadline toAdd;

    /**
     * Constructor for a DeadlineCommand which takes in the relevent Deadline.
     * @param input
     */
    public DeadlineCommand(String input) throws DukeException {
        try {
            String[] splitted = input.split("deadline")[1].split("/by");

            if (splitted.length != 2) {
                throw new DeadlineException();
            }

            if (splitted[0].trim().equals("")) {
                throw new DeadlineException();
            }
            Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
            this.toAdd = toAdd;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DeadlineException();
        }

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
