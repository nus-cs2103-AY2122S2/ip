package duke.Commands;

import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

/**
 * MarkCommand is a subclass of DukeCommand that is created when the user inputs "mark"
 */
public class MarkCommand extends DukeCommand {
    public MarkCommand(String description) {
        super(description);
    }

    /**
     * Marks a task in the list as completed if the user inputs a valid number
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;

            assert index >= tasks.getSize() || index < 0 : "Invalid indexing of taskList from mark command";

            Task task = tasks.get(index);
            task.toggleCompleted();
            storage.save(tasks);
            return ui.showMarkReply(task);
        } catch(IndexOutOfBoundsException | IOException e) {
            if (tasks.getSize() == 0) {
                return ("List is empty! Please add a task before removing/marking it.\n");
            } else {
                return ("Please enter a valid number in the list!\n");
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number";
        }
    }
}
