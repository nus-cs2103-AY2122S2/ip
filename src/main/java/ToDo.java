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

  @Override
  public String toString() {
    String str = "[T]";
    if (super.done) {
      str += "[X] " + super.desc;
    } else {
      str += "[ ] " + super.desc;
    }
    return str;
  }

}
