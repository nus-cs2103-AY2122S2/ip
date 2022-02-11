package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.task.tasks.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) {
        callStack.pushState(taskList);
        return taskList.addTask(task);
    }
}
