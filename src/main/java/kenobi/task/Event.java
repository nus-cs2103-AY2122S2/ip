package kenobi.task;

import java.time.LocalDate;

public class Event extends Task {
  private LocalDate at;

  public Event(String name, LocalDate at) throws TaskException {
    super(name);
    this.at = at;
  }

  @Override
  public Type type() {
    return Type.EVENT;
  }

  @Override
  public String toSave() {
    int doneBit = isDone ? 1 : 0;
    return String.format("E,.,%d,.,%s,.,%s\n", doneBit, name, at);
  }

  @Override
  public String toString() {
    return String.format("[E]%s (at: %s)", super.toString(), formatDate(at));
  }
}
