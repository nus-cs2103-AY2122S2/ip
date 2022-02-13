package Commands;

import Tasks.Task;
import util.*;
import Tasks.TaskList;

import java.io.IOException;

public class UnmarkCommand extends DukeCommand {
    public UnmarkCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "unmark"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;

            assert index >= tasks.getSize() || index < 0 : "Invalid indexing of taskList from ummark command";

            Task task = tasks.get(index);
            task.toggleUncompleted();
            storage.save(tasks);
            return ui.showUnmarkReply(task);
        } catch(IndexOutOfBoundsException | IOException e) {
            if (tasks.getSize() == 0) {
                return ("List is empty! Please add a task before removing/marking it.\n");
            } else {
                return ("Please enter a valid number in the list!\n");
            }
        }
    }
}
