package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;
import duke.task.Task;

public class DeleteTaskCommand extends Command {
    private int taskNo;

    public DeleteTaskCommand(int taskNo) {
        super();
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.delete(taskNo);
        ui.print("Noted. I've removed this task:");
        ui.print(task.toString());
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
