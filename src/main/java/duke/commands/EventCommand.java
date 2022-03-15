package duke.commands;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;

/**
 * Represents a <code>EventCommand</code> which is called when the program adds a Event.
 */
public class EventCommand implements Command {
    private Event toAdd;

    /**
     * Constructor for an EventCommand. Takes in the relevant Event.
     * @param toAdd
     */
    public EventCommand(Event toAdd) {
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
