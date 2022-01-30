public class Task {
  private final String name;
  private boolean done;

  public Task(String name) {
    this.name = name;
    this.done = false;
  }

  public void done() {
    done = true;
  }

  public void undone() {
    done = false;
  }

  @Override
  public String toString() {
    String check = this.done ? "X" : " ";
    return String.format("[%s] %s", check, this.name);
  }
}
