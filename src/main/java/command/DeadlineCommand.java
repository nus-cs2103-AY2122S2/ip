package command;

import command.Command;
import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    protected Task deadline;

    public DeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
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
