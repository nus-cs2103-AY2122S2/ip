package command;

import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

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
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");

    }
}
