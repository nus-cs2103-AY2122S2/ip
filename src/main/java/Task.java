public class Task {
  protected final String name;
  protected int status; // 0: undone, 1: done
  protected static int noOfTasks = 0;

  Task(String taskName) {
    this.name = taskName;
    this.status = 0;
    Task.noOfTasks++;
  }

  public static int getNoOfTasks() {
    return Task.noOfTasks;
  }

  public static void removeTask() {
    Task.noOfTasks--;
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
    return "[" + (this.isDone() ? "X" : " ") // get the icon according to the status
            + "] " + this.name;
  }
}