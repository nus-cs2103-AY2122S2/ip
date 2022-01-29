package van;

/**
 * extends task to represent to do tasks
 */
public class ToDo extends Task {

  /**
   * generates a to do object with the description of the task
   *
   * @param description description of the to do task
   */
  public ToDo(String description) {
    super(description);
  }

  /**
   * Generates string for type of task, completion status
   * and description for storage.
   *
   * @return string with details of to do for storage
   */
  @Override
  public String getStatus() {
    return "[T]" + super.getStatus();
  }

  /**
   * Generates string for type of task, completion status
   * and description for printing by ui.
   *
   * @return string with details of to do for printing
   */
  @Override
  public String saveStatus() {
    return "T|" + super.getCompletion() + "|" + description;
  }
}
