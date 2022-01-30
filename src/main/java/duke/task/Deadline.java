package duke.task;

public class Deadline extends Task {
  private String by;

  public Deadline(String name, String by) {
    super(name);
    this.by = by;
  }

  public Type type() {
    return Type.DEADLINE;
  }

  @Override
  public String toSave() {
    int doneBit = isDone ? 1 : 0;
    return String.format("D,.,%d,.,%s,.,%s\n", doneBit, name, by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.by);
  }
}
