package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(event);
        ui.showTaskAdded(event);
        ui.showNumberOfTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
