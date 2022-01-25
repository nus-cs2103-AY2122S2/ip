public class Todo extends Task {

  public Todo(String description) {
    super(description);
  }

  @Override
  public String getStatus() {
    return "[T]" + super.getStatus();
  }

  @Override
  public String saveStatus() {
    return "T|" + super.getCompletion() + "|" + description;
  }
}
