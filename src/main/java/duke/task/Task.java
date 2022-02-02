package duke.task;

public abstract class Task {

  private String description;
  private boolean isMarked;
  
  public Task(String description, boolean isMarked) {
    this.description = description;
    this.isMarked = isMarked;
  }

  public Task(String description) {
    this(description, false);
  }

  /**
   * Marks this task as done.
   */
  public void mark() {
    isMarked = true;
  }

  /**
   * Marks this task as not done.
   */
  public void unmark() {
    isMarked = false;
  }
  
  /**
   * Marks this task as not done.
   *
   * @return Task description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Marks this task as not done.
   *
   * @return Task description.
   */
  public boolean isMarked() {
    return isMarked;
  }

  /**
   * Returns string representation of this class.
   *
   * @return String representation of this task.
   */
  public abstract String toString();

}