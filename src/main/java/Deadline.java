import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  private String type = "D";
  private LocalDateTime dateTime;

  Deadline(String description, String dateAndTime) {
    super(description);
    this.dateTime = LocalDateTime.parse(dateAndTime);
  }

  @Override
  public String toString() {
    return "[" + this.type + "]" + super.toString() + " (by: " + super.localDateTimeToString(this.dateTime) + ")";
  }
}
