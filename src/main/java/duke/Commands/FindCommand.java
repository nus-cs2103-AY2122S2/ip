package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Tasks.TaskList;
import duke.Tasks.Task;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

/**
 * FindCommand is a subclass of DukeCommand that is created when the user inputs "find"
 */
public class FindCommand extends DukeCommand {
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Finds the tasks that contain what the user inputted
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     * @return String of tasks
     * @throws DukeException
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        TaskList arrListResult = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(this.commandBody)) {
                arrListResult.add(task);
            }
        }

        if (arrListResult.getSize() == 0 ){
            return ui.showEmptyFind();
        } else {
            return ui.showTaskList(arrListResult);
        }
    }
}
