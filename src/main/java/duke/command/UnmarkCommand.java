package duke.command;

public class UnarkCommand {
  
  private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(taskNumber);
    }

}