package van;
public class DeleteCommand implements Command{
  private int taskNumber;

  public DeleteCommand (int taskNumber) {
    this.taskNumber = taskNumber;
  }

  public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
    try {
      if (taskNumber > taskList.getTaskCount()) {
        throw new VanException("Task number out of range");
      }
      taskList.deleteTask(taskNumber);
    } catch (VanException ex) {
      System.out.println(ex);
    }
    return false;
  }
}
