package van;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDateTime date;

  public Deadline(String description, LocalDateTime date) {
    super(description);
    this.date = date;
  }
  @Override
  public String saveStatus() {
    return "D|" + super.getCompletion() + "|" + description + "|" +
      date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
  }

  @Override
  public String getStatus() {
    return "[D]" + super.getStatus() + " (by:" +
      date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
  }
}
