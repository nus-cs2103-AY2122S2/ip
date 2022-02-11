package Commands;

import Tasks.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;


import Exceptions.DukeException;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends DukeCommand {

    public EventCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "execute"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.commandBody);
        LocalDateTime localDateTime = Parser.parseDateTime(this.commandBody, "event");

        Tasks.Event eventTask = new Tasks.Event(deadlineName, localDateTime);

        assert eventTask.getDescription().equals(deadlineName) : "Event object not correctly created";

        tasks.add(eventTask);

        storage.save(tasks);
        return ui.showSuccessfulAdd(eventTask, tasks.getSize());
    }
}
