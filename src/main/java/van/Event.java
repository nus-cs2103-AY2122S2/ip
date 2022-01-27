package van;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  protected LocalDateTime date;

  public Event(String description, LocalDateTime date) {
    super(description);
    this.date = date;
  }

  @Override
  public String saveStatus() {
    return "E|" + super.getCompletion() + "|" + description + "|" +
      date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
  }

  @Override
  public String getStatus() {
    return "[E]" + super.getStatus() + " (at:" +
      date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
  }
}
