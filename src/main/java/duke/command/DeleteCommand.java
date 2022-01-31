package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
      super();
      this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNumber);
    }

}