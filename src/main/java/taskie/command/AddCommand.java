package taskie.command;

import taskie.storage.Storage;
import taskie.task.Deadline;
import taskie.task.Event;
import taskie.task.Task;
import taskie.task.Todo;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

import java.time.LocalDate;

/**
 * A class that specifies the behavior of a command that adds a task to a task list.
 */
public class AddCommand extends Command {
    private String description;
    private LocalDate date;

    /**
     * Constructs an AddCommand object. Used for event and deadline tasks.
     *
     * @param keyword Name of the command.
     * @param description Description of the task.
     * @param date LocalDate of when the task is due.
     */
    public AddCommand(String keyword, String description, LocalDate date) {
        super(keyword);
        this.description = description;
        this.date = date;
    }

    /**
     * Constructs an AddCommand object. Used for todo tasks.
     *
     * @param keyword Name of the command.
     * @param description Description of the task.
     */
    public AddCommand(String keyword, String description) {
        super(keyword);
        this.description = description;
        this.date = null;
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     * @param response StringBuilder object to append results to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        Task task = null;
        switch (keyword) {
        case "todo":
            task = new Todo(description);
            break;
        case "event":
            task = new Event(description, date);
            break;
        case "deadline":
            task = new Deadline(description, date);
            break;
        default:
            break;
        }
        if (task != null) {
            tasks.add(task);
            storage.save(tasks.list());
            response.append(ui.taskAddedMessage(task, tasks.size()));
            assert response.length() > 0; // response should not be empty
        }
    }
}
