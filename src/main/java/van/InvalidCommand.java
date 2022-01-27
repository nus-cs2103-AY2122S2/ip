package van;

public class InvalidCommand implements Command {
  private String message;
  public InvalidCommand(String message) {
    this.message = message;
  }

  public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
    ui.invalidMessage(message);
    return false;
  }
}
