package van;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * extends task to represent deadlines
 */
public class Deadline extends Task {
  protected LocalDateTime date;

  /**
   * Creates an deadline object with the description of the deadline and
   * the date to be done by
   *
   * @param description description about the deadline
   * @param date deadline of the task
   */
  public Deadline(String description, LocalDateTime date) {
    super(description);
    this.date = date;
  }

  /**
   * Generates string for type of task, completion status
   * ,date and description for storage.
   *
   * @return string with details of deadline for storage
   */
  @Override
  public String saveStatus() {
    return "D|" + super.getCompletion() + "|" + description + "|" +
      date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
  }

  /**
   * Generates string for type of task, completion status
   * ,date and description for printing by ui.
   *
   * @return string with details of deadline for printing
   */
  @Override
  public String getStatus() {
    return "[D]" + super.getStatus() + " (by:" +
      date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
  }
}
