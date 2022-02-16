package duke.command;

import java.time.LocalDateTime;

import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;


public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime dueBy;

    /**
     * Constructor for AddDeadlineCommand
     *
     * @param title The title of a task with deadline
     * @param dueBy The due date of a task
     */
    public AddDeadlineCommand(String title, LocalDateTime dueBy) {
        super(IS_EXIT);
        this.title = title;
        this.dueBy = dueBy;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = new Deadline(title, dueBy);
        tasks.addTask(task);
        return TextUi.showExecutionMessage(Messages.MESSAGE_ADD_DEADLINE, task.toString(), tasks.getSize());
    }
}
