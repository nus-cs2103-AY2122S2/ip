package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    protected Task deadline;

    public DeadlineCommand(String description, String by) throws DukeException {
        try {
            this.deadline = new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("date or time was not formatted correctly. Make sure it is yyyy-MM-dd");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        try {
            storage.store(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        ui.showResponseMessage("deadline");
        ui.showTaskMessage(deadline);
        ui.printTasksCountMessage(tasks);
    }
}
