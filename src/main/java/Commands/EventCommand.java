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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.description);
        LocalDateTime localDateTime = Parser.parseDateTime(description, "event");

        Tasks.Deadline deadlineTask = new Tasks.Deadline(deadlineName, localDateTime);
        tasks.add(deadlineTask);

        storage.save(tasks);
        ui.successfulAdd(deadlineTask, tasks.getSize());
    }
}
