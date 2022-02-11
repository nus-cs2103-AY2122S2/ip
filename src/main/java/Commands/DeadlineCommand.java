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

    /**
     * Executes when the user inputs the keyword "deadline"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.commandBody);
        LocalDateTime localDateTime = Parser.parseDateTime(this.commandBody, "deadline");

        Tasks.Deadline deadlineTask = new Tasks.Deadline(deadlineName, localDateTime);
        tasks.add(deadlineTask);

        storage.save(tasks);
        return ui.showSuccessfulAdd(deadlineTask, tasks.getSize());
    }
}
