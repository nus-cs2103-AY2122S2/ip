package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String task;
    private String by;

    public AddDeadlineCommand(String task, String by) {
        super();
        this.task = task;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(task, by);
        taskList.add(deadline);
        ui.print("Got it. I've added this task:");
        ui.print(deadline.toString());
        ui.print("Now you have " + taskList.numOfTasks() + " tasks in the list.");
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}