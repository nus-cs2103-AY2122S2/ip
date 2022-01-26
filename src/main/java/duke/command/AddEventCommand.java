package duke.command;

import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime eventAt;

    public AddEventCommand(String title, LocalDateTime eventAt) {
        super(IS_EXIT);
        this.title = title;
        this.eventAt = eventAt;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = new Event(title, eventAt);
        tasks.addTask(task);
        ui.showExecutionMessage(Messages.MESSAGE_ADD_EVENT, task.toString(), tasks.getSize());
    }
}
