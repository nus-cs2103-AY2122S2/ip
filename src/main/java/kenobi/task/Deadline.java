package kenobi.task;

import java.time.LocalDate;

public class Deadline extends Task {
  private LocalDate by;

  public Deadline(String name, LocalDate by) throws TaskException {
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
    return String.format("[D]%s (by: %s)", super.toString(), formatDate(by));
  }
}
