public class Task {
  private final String name;
  private char type; // T: ToDo, D: Deadlines, E: Events
  private String date;
  private int status; // 0: undone, 1: done
  protected static int noOfTasks = 0;

  Task(String taskName, char taskType, String date) {
    this.name = taskName;
    this.type = taskType;
    this.date = date;
    this.status = 0;
    Task.noOfTasks++;
  }

  public static int getNoOfTasks() {
    return Task.noOfTasks;
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
    return "[" + this.type + "]["
            + (this.isDone() ? "X" : " ") // get the icon according to the status
            + "] " + this.name
            + (this.type == 'T' ? "" :
                (this.type == 'D' ? "(by: " + this.date + ")" : "(at: " + this.date + ")"));
  }
}