package duke.Commands;

import duke.Tasks.Event;
import duke.Tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import duke.Exceptions.DukeException;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * EventCommand is a subclass of DukeCommand that is created when the user inputs "event"
 */
public class EventCommand extends DukeCommand {

    public EventCommand(String description) {
        super(description);
    }

    /**
     * Deals with the creation of an event object and adds it into the task list if the user inputs
     * are valid
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.commandBody);
        LocalDateTime localDateTime = Parser.parseDateTime(this.commandBody, "event");

        Event eventTask = new Event(deadlineName, localDateTime);

        assert eventTask.getDescription().equals(deadlineName) : "Event object not correctly created";

        tasks.add(eventTask);

        storage.save(tasks);
        return ui.showSuccessfulAdd(eventTask, tasks.getSize());
    }
}
