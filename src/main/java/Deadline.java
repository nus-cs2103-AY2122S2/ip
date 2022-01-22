public class Deadline extends Task {

  private String date;

  public Deadline(String str, boolean status, String date) {
    super(str, status);
    this.date = date;
  }

  public Deadline(String str, String date) {
    Deadline(str, false, date);
  }

  public String toString() {
    return String.format("[D][%s] %s (by: %s)", super.getStatus(), super.getStr(), date);
  }
}