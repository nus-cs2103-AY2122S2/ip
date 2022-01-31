package duke.event;

import duke.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  private LocalDate date;

  public Event(String description, boolean status, LocalDate date) {
    super(description, status);
    this.date = date;
  }

  public Event(String description, LocalDate date) {
    this(description, false, date);
  }

  public String toString() {
    return String.format("[E][%s] %s (at: %s)", super.isMarked(), super.getDescription(), DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}