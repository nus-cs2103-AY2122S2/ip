package duke.command;

import java.time.LocalDateTime;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

/**
 * Represents a Command which, when executed, adds a Deadline object into a given TaskList instance.
 */
public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;
    private LocalDateTime deadline;

    /**
     * Creates a new AddDeadlineCommand instance with the initialized description and deadline.
     *
     * @param description Description of the Deadline object to be added.
     * @param deadline Deadline of the Deadline object to be added.
     */
    public AddDeadlineCommand(String description, LocalDateTime deadline) {
        super(IS_EXIT);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Adds the Deadline object to the TaskList and displays the newly added Deadline on Ui.
     *
     * @param tasks The TaskList instance in which the Deadline object is added into.
     * @param ui The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.add(newDeadline);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                Ui.ADD_MESSAGE, newDeadline, tasks.getSize());
        ui.appendMessage(message);
    }
}
