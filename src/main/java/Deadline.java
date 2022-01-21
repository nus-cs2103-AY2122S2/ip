import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDateTime dateTime;

  public Deadline(String description, LocalDateTime dateTime) {
    super(description);
    this.dateTime = dateTime;
  }

  public String getBy() {
    return dateTime.format(DateTimeFormatter.ofPattern("MMM dd YYYY Ka"));
  }
  
  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), getBy());
  }
}
