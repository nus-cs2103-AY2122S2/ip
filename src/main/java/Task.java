import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
  // class constants
  private static final String COMPLETION_MARK = "X";
  private static final String INCOMPLETE_MARK = " ";
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");


  // instance attributes
  private String description;
  private boolean completed;


  Task(String description) {
    this.description = description;
    this.completed = false;
  }

  private String check_if_complete() {
    if (this.completed) {
      return Task.COMPLETION_MARK;
    } else {
      return Task.INCOMPLETE_MARK;
    }
  }

  public void mark_complete() {
    this.completed = true;
  }

  public void mark_incomplete() {
    this.completed = false;
  }

  public static String localDateTimeToString(LocalDateTime dateTime) {
    return dateTime.format(Task.dateTimeFormatter);
  }

  @Override
  public String toString() {
    return "[" + this.check_if_complete() + "] " + this.description;
  }
}
