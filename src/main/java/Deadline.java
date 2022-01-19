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
   * @param date the deadline of the task
   */
  public Deadline(String desc, String date) {
    super(desc);
    this.date = date;
  }

  /**
   * This method is used to get the current status + deadline of the Task
   * Status can be defined as Done/Not Done
   * Task Type is denoted as [D]
   *
   * @return a string denoting the task status, type and date.
   */
  @Override
  public String getTask() {
    String str = "[D]";

    if (super.done) {
      str += "[X] " + super.desc;
    } else {
      str += "[ ] " + super.desc;
    }
    if (date != null) {
      String[] adjDate = date.split(" ");
      str = str + " (" + adjDate[0] + ": ";
      String[] newAdjDate = new String[adjDate.length - 1];
      for (int i = 1; i < adjDate.length; i++) {
        newAdjDate[i - 1] = adjDate[i];
      }
      String adjStr = String.join(" ", newAdjDate);
      str = str + adjStr + ")";
    }
    return str;
  }
}
