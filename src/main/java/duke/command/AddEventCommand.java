package duke.command;

import java.time.LocalDateTime;

import duke.main.Storage;
import duke.main.TaskList;
import duke.task.Event;
import duke.ui.ImageType;
import duke.ui.Ui;

/**
 * Represents a Command which, when executed, adds an Event object into a given TaskList instance.
 */
public class AddEventCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;
    private LocalDateTime dateTime;

    /**
     * Creates a new AddEventCommand instance with the initialized description and deadline.
     *
     * @param description Description of the Event object to be added.
     * @param dateTime Date and Time of the Event object to be added.
     */
    public AddEventCommand(String description, LocalDateTime dateTime) {
        super(IS_EXIT);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Adds the Event object to the TaskList and displays the newly added Event on Ui.
     *
     * @param tasks The TaskList instance in which the Event object is added into.
     * @param ui The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        Event newEvent = new Event(description, dateTime);
        tasks.add(newEvent);
        ui.appendMessage(Ui.ADD_MESSAGE);
        ui.appendBorder();
        ui.appendMessage(newEvent.toString());
        ui.appendBorder();
        ui.appendMessage(String.format("\nThere are %d tasks in the burning list.", tasks.getSize()));
        ui.setRespondImage(ImageType.ADD_OR_DELETE);
    }
}
