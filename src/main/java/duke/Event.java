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
   * Assigns desc, date and done to this instance.
   *
   * @param desc the task description.
   * @param date the date of the event.
   * @param done the current completion status of the task.
   */
  public Event(String desc, LocalDate date, boolean done) {
    super(desc, done);
    this.date = date;
  }

  @Override
  public String toString() {
    String str = "[E]";
    // Convert Current Date Format to MMM dd yyyy
    String customPattern = "MMM dd yyyy";
    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(customPattern);
    String tempDate = customFormat.format(this.date);
    if (super.done) {
      str += "[X] " + super.desc + " (by: " + tempDate + ")";
    } else {
      str += "[ ] " + super.desc + " (by: " + tempDate + ")";
    }
    return str;
  }

  /**
   * Changes the string format of this event object.
   *
   * @return a string in this format E,{done},{desc},{date}.
   */
  @Override
  public String changeFormat() {
    if (super.done) {
      return "E,1," + super.desc + "," + this.date;
    } else {
      return "E,0," + super.desc + "," + this.date;
    }
  }

}
