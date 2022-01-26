package duke.command;

import duke.managers.FileManager;
import duke.managers.TaskList;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

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
    public void executeTask(TaskList taskList, FileManager fileManager) {
        taskList.addTask(this.task, true);
    }

}
