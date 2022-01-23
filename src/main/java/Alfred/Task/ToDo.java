package Alfred.Task;

public class ToDo extends Task {
  String type = "T";

  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[" + this.type + "]" + super.toString();
  }

}
