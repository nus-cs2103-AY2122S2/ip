package duke.command;

public class MarkCommand {
  
  private int taskNumber;

    public MarkCommand(int taskNumber) {
      super();
      this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(taskNumber);
    }

}