package duke.command;

import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.TextUi;

public class AddTodoCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;

    public AddTodoCommand(String title) {
        super(IS_EXIT);
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = new Todo(title);
        tasks.addTask(task);
        ui.showExecutionMessage(Messages.MESSAGE_ADD_TODO, task.toString(), tasks.getSize());
    }
}
