import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
  protected LocalDateTime at;

  Event(String taskName, LocalDateTime datetime) {
    super(taskName);
    this.at = datetime;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString()
            + String.format(" (at: %s)", this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
  }
}