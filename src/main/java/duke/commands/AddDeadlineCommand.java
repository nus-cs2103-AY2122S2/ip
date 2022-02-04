package duke.commands;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Deadline;
import duke.tasks.Task;


/**
 * AddDeadlineCommand is a Command.
 * This Command is used to add Deadlines to the TaskList.
 */
public class AddDeadlineCommand extends Command<String> {
    private final Storage storage;

    /**
     * Constructor for AddDeadlineCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     * @param storage  the textfile used to store history of TaskList
     * @throws DukeException if no Task description is given
     */
    public AddDeadlineCommand(TaskList toDoList, String cmd, Storage storage) throws DukeException {
        this.storage = storage;
        this.runCommand(toDoList, cmd);
    }

    /**
     * Adds a new Deadline Task to the current TaskList
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     * @throws DukeException if no Task description is given
     */
    public void runCommand(TaskList toDoList, String cmd) throws DukeException {
        try {
            // Reformat the command to get the Task's description and deadline
            String[] deadlineDetails = cmd.split("deadline")[1].split("/by");
            String deadlineName = deadlineDetails[0];
            String deadline = deadlineDetails[1];

            // Create a new Deadline to add to TaskList
            Deadline newDeadline = new Deadline(deadlineName, false, deadline);
            assert newDeadline instanceof Deadline;
            toDoList.add(newDeadline);

            // Print out the formatted message after adding to TaskList
            Ui.setDukeResponse(Parser.formatMsg("Got it. I've added this task:\n" + newDeadline
                    + "\nNow you have " + toDoList.size() + " tasks in the list."));

            // Write the contents of the TaskList to our storage
            storage.writeFileContent(toDoList);
        } catch (IndexOutOfBoundsException e) {
            Ui.setDukeResponseError(Parser.formatMsg("OOPS!!! The description of a deadline cannot be empty."));
            throw new DukeException(Parser.formatMsg("OOPS!!! The description of a deadline cannot be empty."));
        } catch (IOException e) {
            throw new DukeException(Parser.formatMsg("IOException caught") + e);
        }
    }
}
