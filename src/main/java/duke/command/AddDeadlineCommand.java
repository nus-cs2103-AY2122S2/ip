package duke.command;

import java.time.LocalDateTime;

import duke.main.Storage;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.ui.ImageType;
import duke.ui.Ui;
import duke.utils.Priority;

/**
 * Represents a Command which, when executed, adds a Deadline object into a given TaskList instance.
 */
public class AddDeadlineCommand extends Command {
    private static final String ENDING_MESSAGE_FORMAT = "\nThere are %d tasks in the burning list.";
    private static final boolean IS_EXIT = false;
    private String description;
    private LocalDateTime deadline;
    private Priority priority;

    /**
     * Creates a new AddDeadlineCommand instance with the initialized description and deadline.
     *
     * @param description Description of the Deadline object to be added.
     * @param deadline Deadline of the Deadline object to be added.'
     * @param priority The Priority object specifying the priority of the Deadline to be added.
     */
    public AddDeadlineCommand(String description, LocalDateTime deadline, Priority priority) {
        super(IS_EXIT);
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    /**
     * Adds the Deadline object to the TaskList and displays the newly added Deadline on Ui.
     *
     * @param tasks The TaskList instance in which the Deadline object is added into.
     * @param ui The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        Deadline newDeadline = new Deadline(description, deadline, priority);
        tasks.add(newDeadline);
        ui.appendMessage(Ui.ADD_MESSAGE);
        ui.appendBorder();
        ui.appendMessage(newDeadline.toString());
        ui.appendBorder();
        ui.appendMessage(String.format(ENDING_MESSAGE_FORMAT, tasks.getSize()));
        ui.setRespondImage(ImageType.ADD_OR_DELETE);
    }
}
