package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    private final Event newEvent;

    public AddEventCommand(Event newEvent) {
        super();
        this.newEvent = newEvent;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new Event(newEvent.getTask(), newEvent.getDate()));
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this duke.task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");

    }
}
