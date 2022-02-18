package duke.Commands;

import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

/**
 * DeleteCommand is a subclass of DukeCommand that is deals with the deletion of
 * items in the task list when the user inputs "delete"
 */
public class DeleteCommand extends DukeCommand {
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Deals with the deletion of a task in the list if the user inputs a valid number
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;
            Task task = tasks.delete(index);
            storage.save(tasks);
            return ui.showDeleteReply(task, tasks.getSize());
        } catch (IndexOutOfBoundsException | IOException e) {
            if (tasks.getSize() == 0) {
                return ("List is empty! Please add a task before removing/marking it.\n");
            } else {
                return ("Please enter a valid number in the list!");
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number";
        }
    }
}