package duke.commands;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IndexException;

import java.time.format.DateTimeParseException;

/**
 * Represents a <code>UpdateEventCommand</code> which is called to alter an Event's timing.
 */

public class UpdateEventCommand implements Command {
    private int index;
    private String start;
    private String end;

    /**
     * Constructor taking the index and the new start/end timing in YYYY/MM/DD HHMM format.
     * @param input
     */
    public UpdateEventCommand(String input) throws DukeException {
        String[] splitted = input.split(" ");
        if (splitted.length != 8) {
            throw new DukeException("Invalid format for update event time. \n" +
                    "Correct format: update event time <index> " +
                    "<FROM YYYY-MM-DD> <FROM HHMM> <TO YYYY-MM-DD> <TO HHMM>\n");
        }
        int index = Integer.valueOf(splitted[3]);
        String newStartTime = splitted[4] + " " + splitted[5];
        String newEndTime = splitted[6] + " " + splitted[7];
        this.index = index;
        this.start = newStartTime;
        this.end = newEndTime;

    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (index <= 0 || index > tasks.getSize()) {
            throw new IndexException();
        }
        Task oldTask = tasks.get(index - 1);
        if (oldTask instanceof Event) {
            Event oldEventTask = (Event) oldTask;
            try {
                tasks.set(index - 1, new Event(oldEventTask.getName(), start, end));
                return "Successfully altered the event. The new Event task at index " + (index) + "\n"
                        + "is " +  tasks.get(index - 1);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid format for update event. \n" +
                        "Correct format: update event time <index> " +
                        "<FROM YYYY-MM-DD> <FROM HHMM> <TO YYYY-MM-DD> <TO HHMM>\n" +
                        "Example: update event time 3 2012-04-03 1500 2012-12-12 1600\n " +
                        "means the event at index 3 now occurs on 12 Dec 2012 at 4PM");
            }
        } else {
            throw new DukeException("The indicated task is not of type Event.");
        }
    }
}
