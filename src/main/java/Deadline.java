import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDateTime by;

  Deadline(String taskName, LocalDateTime date) {
    super(taskName);
    this.by = date;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString()
            + String.format(" (by: %s)", this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
  }
}
