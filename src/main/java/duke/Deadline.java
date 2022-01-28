package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Deadline extends Task {

  private LocalDate date;

  /**
   * Method Constructor
   *
   * @param desc the task description
   * @param date deadline of the task
   * @param isDone the status of the task
   */
  public Deadline(String desc, LocalDate date, boolean isDone) {
    super(desc, isDone);
    this.date = date;
  }

  @Override
  public String toString() {
    String str = "[D]";
    // Convert Current Date Format to MMM dd yyyy
    String customPattern = "MMM dd yyyy";
    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(customPattern);
    String tempDate = customFormat.format(this.date);
    if (super.isDone) {
      str += "[X] " + super.desc + " (by: " + tempDate + ")";
    } else {
      str += "[ ] " + super.desc + " (by: " + tempDate + ")";
    }
    return str;
  }

  @Override
  public String changeFormat() {
    if (super.isDone) {
      return "D,1," + super.desc + "," + this.date;
    } else {
      return "D,0," + super.desc + "," + this.date;
    }
  }
}
