package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand
     *
     * @param userTaskString
     * @param task
     */
    public AddCommand(String userTaskString, Task task) {
        super(userTaskString);
        this.task = task;
    }

    /**
     * Executes adding of task to TaskList (with printing)
     *
     * @param taskList
     * @param fileManager
     */
    @Override
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.addTask(this.task, true);
    }

}
