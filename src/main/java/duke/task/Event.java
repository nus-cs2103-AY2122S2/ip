package duke.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  public Event(String str, boolean status, LocalDate date) {
    super(str, status);
    this.date = date;
  }

  public Event(String str, LocalDate date) {
    Event(str, false, date);
  }

  public String toString() {
    return String.format("[E][%s] %s (at: %s)", i, super.getStatus(), super.getStr(), DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}