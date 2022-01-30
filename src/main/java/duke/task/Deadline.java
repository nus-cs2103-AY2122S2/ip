package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  private LocalDate date;

  public Deadline(String str, boolean status, LocalDate date) {
    super(str, status);
    this.date = date;
  }

  public Deadline(String str, LocalDate date) {
    Deadline(str, false, date);
  }

  public String toString() {
    return String.format("[D][%s] %s (by: %s)", super.getStatus(), super.getStr(), DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}