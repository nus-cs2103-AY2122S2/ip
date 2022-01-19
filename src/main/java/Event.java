public class Event extends Task {
  protected String at;

  Event(String taskName, String date) {
    super(taskName);
    this.at = date;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString()
            + String.format(" (at: %s)", this.at);
  }
}