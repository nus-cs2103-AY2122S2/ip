public class Deadline extends Task {
  protected String date;

  public Deadline(String description, String date) {
    super(description);
    this.date = date;
  }

  @Override
  public String getStatus() {
    return "[D]" + super.getStatus() + " (by:" + date + ")";
  }
}
