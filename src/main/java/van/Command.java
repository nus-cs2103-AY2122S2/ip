package van;

/**
 * Interface for all types of Commands
 */
public interface Command {

  /**
   * Calls all the methods from ui, tasklist and storage in order
   * to execute the different command types.
   *
   * @param ui Ui object to handle print tasks
   * @param taskList TaskList object that handles managing of the list of tasks
   * @param storage Storage object that handles loading and saving of list
   * @return returns true only when command executed is an ExitCommand
   */
  boolean executeCommand(Ui ui, TaskList taskList, Storage storage);
}
