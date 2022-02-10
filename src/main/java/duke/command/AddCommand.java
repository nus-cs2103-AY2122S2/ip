package duke.command;

import duke.task.TaskList;
import duke.task.tasks.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.addTask(task);
    }
}
