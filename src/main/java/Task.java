public class Task {

  private final int index;
  private final String title;
  private boolean done;

  public Task(int index, String title) {
    this.index = index;
    this.title = title;
    this.done = false;
  }

  public boolean isCompleted() {
    return done;
  }

  public void mark() {
    done = true;
  }

  public void unmark() {
    done = false;
  }

  @Override
  public String toString() {
    return String.format(
      "             %d. [%s] %s",
      index,
      done ? "X" : " ",
      title
    );
  }
}
