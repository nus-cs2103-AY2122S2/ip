package duke.commands;

import duke.exceptions.EventException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;

import java.time.format.DateTimeParseException;

/**
 * Represents a <code>EventCommand</code> which is called when the program adds a Event.
 */
public class EventCommand implements Command {
    private Event toAdd;

    /**
     * Constructor for an EventCommand. Takes in the relevant Event.
     * @param input
     */
    public EventCommand(String input) throws DukeException {
        if (input.trim().equals("event")) {
            throw new EventException();
        }
        String[] splitted = input.split("event")[1].split("/at");
        if (splitted.length != 2) {
            throw new EventException();
        }
        String name = splitted[0].trim();
        if (name.equals("")) {
            throw new EventException();
        }
        String[] splittedTime = splitted[1].trim().split(" ");
        if (splittedTime.length != 4) {
            throw new EventException();
        }
        String startTime = splittedTime[0] + " " + splittedTime[1];
        String endTime = splittedTime[2] + " " + splittedTime[3];
        try {
            this.toAdd = new Event(name, startTime, endTime);
        } catch (IndexOutOfBoundsException | DateTimeParseException | DukeException e) {
            if (e instanceof DukeException) {
                throw e;
            } else {
                throw new EventException();
            }
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
