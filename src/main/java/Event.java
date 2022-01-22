public class Event extends Task {

  public Event(String str, boolean status, String date) {
    super(str, status);
    this.date = date;
  }

  public Event(String str, String date) {
    Event(str, false, date);
  }

  public String toString() {
    return String.format("[E][%s] %s (at: %s)", i, super.getStatus(), super.getStr(), date);
  }
}