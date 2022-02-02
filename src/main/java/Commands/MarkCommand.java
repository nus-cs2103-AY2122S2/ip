package Commands;

import Tasks.Task;
import Tasks.TaskList;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class MarkCommand extends DukeCommand {
    public MarkCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "mark"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;
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
        }
    }
}
