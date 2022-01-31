package duke.todo;

import duke.task.Task;

public class Todo extends Task {

  public Todo(String description, boolean status) {
    super(description, status);
  }

  public Todo(String description) {
    this(description, false);
  }

  public String toString() {
    return String.format("[T][%s] %s", super.isMarked(), super.getDescription());
  }

}