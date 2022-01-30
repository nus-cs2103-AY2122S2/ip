package duke.todo;

public class Todo extends Task {

  public Todo(String str, boolean status) {
    super(str, status);
  }

  public Todo(str) {
    Todo(str, false);
  }

  public String toString() {
    return String.format("[T][%s] %s", i, super.getStatus(), super.getStr());
  }

}