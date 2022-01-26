package luke.commands;

import luke.data.TaskList;
import luke.data.tasks.Task;

public abstract class AddCommand extends Command {

    protected static final String DEFAULT_MESSAGE = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
    private final Task task;


    AddCommand(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.add(task);
        return new CommandResult(String.format(DEFAULT_MESSAGE, this.task, taskList.size()));
    }
}
