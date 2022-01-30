package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime dueDateTime;

    public DeadlineCommand(String taskName, LocalDateTime dueDateTime) {
        this.taskName = taskName;
        this.dueDateTime = dueDateTime;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addDeadline(taskName, dueDateTime);
        ui.sayTaskAddingLines(taskNumber);
    }
}
