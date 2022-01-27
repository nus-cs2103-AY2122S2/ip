package duke.commands;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Event;

/**
 * AddEventCommand is a Command.
 * This Command is used to add Events to the TaskList.
 */
public class AddEventCommand extends Command<String> {
    private final Storage storage;

    /**
     * Constructor for AddEventCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     * @param storage  the textfile used to store history of TaskList
     * @throws DukeException if no Task description is given
     */
    public AddEventCommand(TaskList toDoList, String cmd, Storage storage) throws DukeException {
        this.storage = storage;
        this.runCommand(toDoList, cmd);
    }

    /**
     * Adds a new Event Task to the current TaskList
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     * @throws DukeException if no Task description is given
     */
    public void runCommand(TaskList toDoList, String cmd) throws DukeException {
        try {
            // Reformat the command to get the Task's description and date/time
            String[] eventDetails = cmd.split("event")[1].split("/at");
            String eventName = eventDetails[0];
            String eventDateTime = eventDetails[1];

            // Create a new Event to add into TaskList
            Event newEvent = new Event(eventName, false, eventDateTime);
            toDoList.add(newEvent);

            // Print out the formatted message after adding to TaskList
            Ui.setDukeResponse(Parser.formatMsg("Got it. I've added this task:\n" + newEvent
                    + "\nNow you have " + toDoList.size() + " tasks in the list."));

            // Write the contents of the TaskList to our storage
            storage.writeFileContent(toDoList);
        } catch (IndexOutOfBoundsException e) {
            Ui.setDukeResponseError(Parser.formatMsg("☹ OOPS!!! The description of an event cannot be empty."));
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! The description of an event cannot be empty."));
        } catch (IOException e) {
            throw new DukeException(Parser.formatMsg("IOException caught") + e);
        }
    }
}
