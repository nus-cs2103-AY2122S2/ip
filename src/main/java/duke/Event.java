package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Event extends Task {

  private LocalDate date;

  /**
   * Method Constructor
   *
   * @param desc the task description
   * @param date of the event
   * @param isDone the current completion status of the task.
   */
  public Event(String desc, LocalDate date, boolean isDone) {
    super(desc, isDone);
    this.date = date;
  }

  @Override
  public String toString() {
    String str = "[E]";
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
      return "E,1," + super.desc + "," + this.date;
    } else {
      return "E,0," + super.desc + "," + this.date;
    }
  }

}
