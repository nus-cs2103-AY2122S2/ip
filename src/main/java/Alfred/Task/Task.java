package Alfred.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Task {
  // class constants
  private static final String COMPLETION_MARK = "X";
  private static final String INCOMPLETE_MARK = " ";
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
      FormatStyle.MEDIUM, FormatStyle.MEDIUM);


  // instance attributes
  private final String description;
  private boolean completed;


  Task(String description) {
    this.description = description;
    this.completed = false;
  }

  private String markIfComplete() {
    if (this.completed) {
      return Task.COMPLETION_MARK;
    } else {
      return Task.INCOMPLETE_MARK;
    }
  }

  public void markComplete() {
    this.completed = true;
  }

  public void markIncomplete() {
    this.completed = false;
  }

  public static String localDateTimeToString(LocalDateTime dateTime) {
    return dateTime.format(Task.dateTimeFormatter);
  }

  @Override
  public String toString() {
    return "[" + this.markIfComplete() + "] " + this.description;
  }
}
