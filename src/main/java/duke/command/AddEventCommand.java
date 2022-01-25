package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;
    private LocalDateTime dateTime;

    public AddEventCommand(String description, LocalDateTime dateTime) {
        super(IS_EXIT);
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, dateTime);
        tasks.add(newEvent);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                Ui.ADD_MESSAGE, newEvent, tasks.getSize());
        ui.appendMessage(message);
    }
}
