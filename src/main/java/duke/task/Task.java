package duke.task;

public abstract class Task {

  private String description;
  private boolean isMarked;
  
  public Task(String description, boolean isMarked) {
    this.description = description;
    this.isMarked = isMarked;
  }

  public Task(String description) {
    this(description, false);
  }

  public void mark() {
    isMarked = true;
  }

  public void unmark() {
    isMarked = false;
  }

  public String getDescription() {
    return description;
  }

  public boolean isMarked() {
    return isMarked;
  }

  public abstract String toString();

}