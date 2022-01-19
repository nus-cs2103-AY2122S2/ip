public class Deadline extends Task {
  protected String by;

  Deadline(String taskName, String date) {
    super(taskName);
    this.by = date;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString()
            + String.format(" (by: %s)", this.by);
  }
}
