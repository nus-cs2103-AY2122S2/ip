package duke.task;

public abstract class Task {
  protected final String name;
  protected boolean isDone;

  public enum Type {
    DEADLINE,
    EVENT,
    TODO,
  }

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public Task done() {
    isDone = true;
    return this;
  }

  public Task undone() {
    isDone = false;
    return this;
  }

  public abstract Type type();
  public abstract String toSave();

  @Override
  public String toString() {
    String check = this.isDone ? "X" : " ";
    return String.format("[%s] %s", check, this.name);
  }
}
