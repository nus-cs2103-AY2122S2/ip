package Commands;

import Exceptions.DukeException;
import Tasks.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;


import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends DukeCommand {

    public DeadlineCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.commandBody);
        LocalDateTime localDateTime = Parser.parseDateTime(this.commandBody, "deadline");

        Tasks.Deadline deadlineTask = new Tasks.Deadline(deadlineName, localDateTime);
        tasks.add(deadlineTask);

        storage.save(tasks);
        ui.showSuccessfulAdd(deadlineTask, tasks.getSize());

    }
}
