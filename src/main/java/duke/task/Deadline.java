package duke.task;

import duke.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  private LocalDate date;

  public Deadline(String description, boolean status, LocalDate date) {
    super(description, status);
    this.date = date;
  }

  public Deadline(String description, LocalDate date) {
    this(description, false, date);
  }

  public String toString() {
    return String.format("[D][%s] %s (by: %s)", super.isMarked(), super.getDescription(), DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}