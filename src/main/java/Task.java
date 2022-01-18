public class Task {
  private final String name;
  private int status; // 0 for undone; 1 for done

  Task(String s) {
    this.name = s;
    this.status = 0;
  }

  public boolean isDone() {
    return status == 1;
  }

  public void mark() {
    this.status = 1;
  }

  public void unmark() {
    this.status = 0;
  }

  @Override
  public String toString() {
    return "[" +
            (this.isDone() ? "X" : " ") // get the icon according to the status
            + "] " + this.name;
  }
}
