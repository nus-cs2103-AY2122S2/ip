package van;

/**
 * DeleteCommand abstracts the deletion of tasks from the list
 */
public class DeleteCommand implements Command{
  private int taskNumber;

  /**
   * Creates a DeleteCommand object to delete a certain task
   *
   * @param taskNumber number of task to be deleted
   */
  public DeleteCommand (int taskNumber) {
    this.taskNumber = taskNumber;
  }

  /**
   * Calls the methods needed in order to delete a task
   *
   * @param ui Ui object to handle print tasks
   * @param taskList TaskList object that handles managing of the list of tasks
   * @param storage Storage object that handles loading and saving of list
   * @return returns true only when command executed is an ExitCommand
   */
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
