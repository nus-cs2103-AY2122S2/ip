package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import java.time.LocalDate;

/**
 * Represents add event task command
 */
public class AddEventCommand extends Command {
    private String description;
    private String at;
    private LocalDate date;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public AddEventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Adds event task to list
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (date == null) {
            task = new Event(description, at);
        } else {
            task = new Event(description, date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
