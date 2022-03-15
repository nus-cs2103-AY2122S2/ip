package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * Represents a command that creates a new Deadline task.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private LocalDate deadline;
    private String description;

    /**
     * Creates a new DeadlineCommand.
     *
     * @param description Description of deadline.
     * @param deadline Date representing the deadline.
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline task, adds it to the list of tasks, stores the tasks into the user's storage and
     * notifies the user upon successful completion.
     *
     * @param tasks List of user's tasks.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline dl = new Deadline(description, deadline);
        tasks.addTask(dl);
        storage.save(tasks);
        return Ui.showAddTask(dl, tasks.count());
    }
}
