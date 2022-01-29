package van;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * extends task to represent events
 */
public class Event extends Task {
  protected LocalDateTime date;

  /**
   * Creates an event object with the description of the event and
   * the date of the event
   *
   * @param description description about the event
   * @param date date that the event is held
   */
  public Event(String description, LocalDateTime date) {
    super(description);
    this.date = date;
  }

  /**
   * Generates string for type of task, completion status
   * ,date and description for storage.
   *
   * @return string with details of event for storage
   */
  @Override
  public String saveStatus() {
    return "E|" + super.getCompletion() + "|" + description + "|" +
      date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
  }

  /**
   * Generates string for type of task, completion status
   * ,date and description for printing by ui.
   *
   * @return string with details of event for printing
   */
  @Override
  public String getStatus() {
    return "[E]" + super.getStatus() + " (at:" +
      date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
  }
}
