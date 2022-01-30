abstract class Task {
  protected final String name;
  protected boolean isDone;

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public void done() {
    isDone = true;
  }

  public void undone() {
    isDone = false;
  }

  abstract String toSave();

  @Override
  public String toString() {
    String check = this.isDone ? "X" : " ";
    return String.format("[%s] %s", check, this.name);
  }
}
