package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class UnmarkCommand extends Command {
  
  private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(taskNumber);
    }

}