public class Deadline extends Task {
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public String getBy() {
    return by;
  }

  @Override
  public String getSaveDescription() {
    return String.format("%s | %s | %s | %s" ,
        getClass().getName(), status == true ? 1 : 0, description, getBy());
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), getBy());
  }
}
