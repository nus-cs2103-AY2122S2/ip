package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to generate a list of upcoming task in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class RemindersCommand extends Command {

    private final int dayRange;

    /**
     * Constructor to create a Reminders Command.
     *
     * @param i The number of days ahead for task to be included in the reminder.
     */
    public RemindersCommand(int i) {
        this.dayRange = i;
    }

    /**
     * Execute the command to print the tasks in range.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException Invalid task id (Either already completed or out or range).
     * @exception IOException
     * @see IOException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.MSG_EMPTYTASK);
        } else {
            return Ui.reminderListMsg(taskList, dayRange);
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return "To list all reminder(s) in day range.\n"
                + "  Usage: reminders <days> | i.e. reminders 7\n\n";

    }

}
