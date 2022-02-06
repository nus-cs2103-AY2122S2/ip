package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * An instance of AddCommand
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String taskDetails;

    /**
     * Instantiates a new Add command.
     *
     * @param taskType    the task type
     * @param taskDetails the task details
     */
    public AddCommand(String taskType, String taskDetails) {
        this.taskType = taskType.toLowerCase();
        this.taskDetails = taskDetails;
    }

    /**
     * Adds the task into this `TaskList` and prints out a message to show what has been added.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to inform the user that the specific task has been added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        switch (taskType) {
        case "todo":
            tasks.addTask(task = new ToDo(taskDetails));
            return ui.showAdditionOrDeletion(this, tasks.size(), task);
        case "event":
            String[] eventDetails = taskDetails.split(" /at ", 2);
            try {
                tasks.addTask(task = new Event(eventDetails[0], eventDetails[1]));
                return ui.showAdditionOrDeletion(this, tasks.size(), task);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        case "deadline":
            String[] deadlineDetails = taskDetails.split(" /by ", 2);
            try {
                tasks.addTask(task = new Deadline(deadlineDetails[0], deadlineDetails[1]));
                return ui.showAdditionOrDeletion(this, tasks.size(), task);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        default:
            return ui.showError("Invalid command detected in AddCommand.");
        }
    }
}
