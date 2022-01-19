/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class ToDo extends Task {

  /**
   * Method Constructor
   *
   * @param desc the task description
   */
  public ToDo(String desc) {
    super(desc);
  }

  /**
   * This method is used to get the current status of the Task
   * Status can be defined as Done/Not Done
   * Task Type is denoted as [T]
   *
   * @return a string denoting the task status and type.
   */
  @Override
  public String getTask() {
    String str = "[T]";
    if (super.done) {
      str += "[X] " + super.desc;
    } else {
      str += "[ ] " + super.desc;
    }
    return str;
  }
}
