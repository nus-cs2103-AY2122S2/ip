/**
 * This class specifies all the methods and variables of a task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public abstract class Task {
  // Class variables
  protected boolean done = false;
  protected String desc;

  /**
   * Method Constructor
   *
   * @param desc the task description
   */
  public Task(String desc) {
    this.desc = desc;
  }

  /**
   * This abstract method is used to get the current status of the Task
   * Status can be defined as Done/Not Done
   *
   * @return a string denoting the task status.
   */
  public abstract String getTask();

  /**
   * This method is used to change the status of the Task to done.
   */
  public void setAsDone() {
    this.done = true;
  }

  /**
   * This method is used to change the status of the Task to not done.
   */
  public void setAsNotDone() {
    this.done = false;
  }

}
