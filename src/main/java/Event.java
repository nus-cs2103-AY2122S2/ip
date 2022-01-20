public class Event extends Task {

  protected String date;

  public Event(String description, String date) {
    super(description);
    this.date = date;
  }

  @Override
  public String getStatus() {
    return "[E]" + super.getStatus() + " (at:" + date + ")";
  }
}
