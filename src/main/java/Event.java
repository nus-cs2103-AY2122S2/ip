/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Event extends Task {

  private String date;

  /**
   * Method Constructor
   *
   * @param desc the task description
   * @param date of the event
   */
  public Event(String desc, String date, boolean done) {
    super(desc, done);
    this.date = date;
  }

  @Override
  public String toString() {
    String str = "[E]";
    if (super.done) {
      str += "[X] " + super.desc + " (at: " + this.date + ")";
    } else {
      str += "[ ] " + super.desc + " (at: " + this.date + ")";
    }
    return str;
  }

  @Override
  public String changeFormat() {
    if (super.done) {
      return "E,1," + super.desc + "," + this.date;
    } else {
      return "E,0," + super.desc + "," + this.date;
    }
  }

}
