package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime eventDateTime;

    public EventCommand(String taskName, LocalDateTime eventDateTime) {
        this.taskName = taskName;
        this.eventDateTime = eventDateTime;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addEvent(taskName, eventDateTime);
        ui.sayTaskAddingLines(taskNumber);
    }
}
