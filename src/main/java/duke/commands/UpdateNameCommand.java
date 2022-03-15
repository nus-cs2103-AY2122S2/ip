package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;


/**
 * Represents a <code>UpdateNameCommand</code> which is called to change the name of a task.
 */

public class UpdateNameCommand implements Command {
    private int index;
    private String newName;

    /**
     * Constructor taking the relevant Index and the new name.
     * @param input
     */
    public UpdateNameCommand(String input) throws DukeException {
        String[] splitted = input.split(" ");
        if (splitted.length <= 3) {
            throw new DukeException("Invalid format for update name. \n" +
                    "Correct format: update name <index> <newname>\n" +
                    "Example: update name 3 Borrow Book\n changes the name of the Task at index 3" +
                    " to Borrow Book."
            );
        }
        int index = Integer.valueOf(splitted[2]);
        String newName = "";
        for (int i = 3; i < splitted.length; i++) {
            newName += splitted[i] + " ";
        }
        newName = newName.trim();
        if (newName.equals("")) {
            throw new DukeException("Provided name cannot be empty!");
        }
        this.index = index;
        this.newName = newName;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {

        if (index <= 0 || index > tasks.getSize()) {
            throw new IndexException();
        }

        Task oldTask = tasks.get(index - 1);

        if (newName.equals(oldTask.getName())) {
            throw new DukeException("The original task already has this name!");
        }

        if (oldTask instanceof Todo) {
            tasks.set(index - 1, new Todo(newName));
            return "The Todo at index " + index + " is successfully edited to " + tasks.get(index - 1);
        } else if (oldTask instanceof Deadline) {
            Deadline oldDeadline = (Deadline) oldTask;
            Deadline newDeadline = new Deadline(newName, oldDeadline.getDeadline());
            tasks.set(index - 1, newDeadline);
            return "The Deadline at index " + index + " is successfully edited to " + tasks.get(index - 1);
        } else if (oldTask instanceof Event) {
            Event oldEvent = (Event) oldTask;
            Event newEvent = new Event(newName, oldEvent.getStartString(), oldEvent.getEndString());
            tasks.set(index - 1, newEvent);
            return "The Event at index " + index + " is successfully edited to " + tasks.get(index - 1);
        } else {
            throw new DukeException("Update name command failed. Please reset Duke.");
        }
    }

}
