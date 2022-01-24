package duke.command.add;

import duke.command.Command;
import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.task.Task;
import duke.task.TaskList;

public abstract class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public CommandFeedback execute(TaskList taskList) {
        taskList.add(newTask);
        return new CommandFeedback(CommandType.ADD, taskList, newTask);
    }
}
