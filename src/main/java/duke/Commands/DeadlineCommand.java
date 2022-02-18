package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * DeadlineCommand is a subclass of DukeCommand that is created when the user inputs "deadline"
 */
public class DeadlineCommand extends DukeCommand {

    public DeadlineCommand(String description) {
        super(description);
    }

    /**
     * Deals with the creation of a deadline object and adds it into the task list if the user inputs
     * are valid
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        String deadlineName = Parser.parseDescription(this.commandBody);
        LocalDateTime localDateTime = Parser.parseDateTime(this.commandBody, "deadline");

        Deadline deadlineTask = new Deadline(deadlineName, localDateTime);

        assert deadlineTask.getDescription().equals(deadlineName) : "Deadline object not correctly created";

        tasks.add(deadlineTask);

        storage.save(tasks);
        return ui.showSuccessfulAdd(deadlineTask, tasks.getSize());
    }
}
