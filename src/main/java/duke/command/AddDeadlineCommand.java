package duke.command;

import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime dueBy;

    public AddDeadlineCommand(String title, LocalDateTime dueBy) {
        super(IS_EXIT);
        this.title = title;
        this.dueBy = dueBy;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = new Deadline(title, dueBy);
        tasks.addTask(task);
        ui.showExecutionMessage(Messages.MESSAGE_ADD_DEADLINE, task.toString(), tasks.getSize());

    }
}
