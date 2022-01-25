/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Deadline extends Task {

  private String date;

  /**
   * Method Constructor
   *
   * @param desc the task description
   * @param date deadline of the task
   * @param done the status of the task
   */
  public Deadline(String desc, String date, boolean done) {
    super(desc, done);
    this.date = date;
  }

  @Override
  public String toString() {
    String str = "[D]";
    if (super.done) {
      str += "[X] " + super.desc + " (by: " + this.date + ")";
    } else {
      str += "[ ] " + super.desc + " (by: " + this.date + ")";
    }
    return str;
  }

  @Override
  public String changeFormat() {
    if (super.done) {
      return "D,1," + super.desc + "," + this.date;
    } else {
      return "D,0," + super.desc + "," + this.date;
    }
  }
}
