package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * The UpdateCommand class is a type of Command that is used to update a particular task.
 */
public class UpdateCommand extends Command {
    public UpdateCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the update command and updates the specified task from the TaskList
     * and updates it in the Storage as well.
     *
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     * @throws DukeException If Storage is unable to save the updated task successfully.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.tokenizedCommand[1]) - 1;
        Task t = tasks.getTask(index);
        String oldDetails = t.getSaveFormat();
        String outputMsg = "";
        switch (this.tokenizedCommand[0]) {
        case "mark":
            t.markAsDone();
            outputMsg = "Good job! I've marked this task as done:\n" + t;
            break;
        case "unmark":
            t.markAsNotDone();
            outputMsg = "Okay, I've marked this task as not done yet:\n" + t;
            break;
        default:
            break;
        }
        storage.updateSavedTasks(oldDetails, t.getSaveFormat());
        ui.printMsg(outputMsg);
    }
}
