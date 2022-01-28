package duke;

/**
 * This class specifies all the methods and variables of a task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public abstract class Task {
  // Class variables
  protected boolean isDone;
  protected String desc;

  /**
   * Method Constructor
   *
   * @param desc the task description
   */
  public Task(String desc, boolean isDone) {
    this.desc = desc;
    this.isDone = isDone;
  }

  /**
   * This method is used to change the status of the Task to done.
   */
  public void setAsDone() {
    this.isDone = true;
  }

  /**
   * This method is used to change the status of the Task to not done.
   */
  public void setAsNotDone() {
    this.isDone = false;
  }

  public abstract String changeFormat();
}
