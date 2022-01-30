package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import java.time.LocalDate;

/**
 * Represents add deadline task command
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private LocalDate date;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public AddDeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Adds deadline task to list
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
            task = new Deadline(description, by);
        }
        else {
            task = new Deadline(description, date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
