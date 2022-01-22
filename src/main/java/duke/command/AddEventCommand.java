package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import java.time.LocalDate;

public class AddEventCommand extends Command {
    protected String description;
    protected String at;
    protected LocalDate date;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }
    public AddEventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (date == null) {
            task = new Event(description, at);
        }
        else {
            task = new Event(description, date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
