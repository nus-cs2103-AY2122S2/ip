package duke;

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
   * @param done the status of the task
   */
  public ToDo(String desc, boolean done) {
    super(desc, done);
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

  @Override
  public String changeFormat() {
    if (super.done) {
      return "T,1," + super.desc;
    } else {
      return "T,0,"+ super.desc;
    }
  }

}
