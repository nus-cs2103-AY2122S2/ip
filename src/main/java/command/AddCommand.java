package command;

import duke.DukeException;
import duke.Storage;
import duke.UiForGUI;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * The AddCommand class is a type of Command that is used to add new task.
 */
public class AddCommand extends Command {
    public AddCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the add command and adds the new task to the TaskList and stores it in the Storage as well.
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     * @throws DukeException If Storage is unable to save the new task successfully.
     */
    @Override
    public void execute(TaskList tasks, UiForGUI ui, Storage storage) throws DukeException {
        Task t = null;
        String desc = "";
        switch (this.tokenizedCommand[0]) {
        case "todo":
            t = new Todo(this.command.substring(5));
            break;
        case "deadline":
            int indexOfBy = command.indexOf("/by");
            desc = command.substring(9, indexOfBy - 1);
            String by = command.substring(indexOfBy + 4);
            t = new Deadline(desc, by);
            break;
        case "event":
            int indexOfAt = command.indexOf("/at");
            desc = command.substring(6, indexOfAt - 1);
            String at = command.substring(indexOfAt + 4);
            t = new Event(desc, at);
            break;
        default:
            break;
        }
        tasks.addTask(t);
        storage.updateSavedTasks("", t.getSaveFormat());
        ui.printMsg("Got it. I've added this task:\n  " + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
