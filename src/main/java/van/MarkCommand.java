package van;

public class MarkCommand implements Command{
  private boolean isDone;
  private int index;

  public MarkCommand(boolean isDone, int index) {
    this.isDone = isDone;
    this.index = index;
  }

  public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
    try {
      if (index > taskList.getTaskCount()) {
        throw new VanException("Task number out of range");
      }
      if (isDone) {
        taskList.markDone(index);
      } else {
        taskList.markUndone(index);
      }
    } catch (VanException ex) {
      System.out.println(ex);
    }
    return false;
  }
}
