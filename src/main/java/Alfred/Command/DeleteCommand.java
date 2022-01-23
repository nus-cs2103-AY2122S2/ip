package Alfred.Command;

import Alfred.Exceptions.InvalidIndexException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

public class DeleteCommand extends Command {
  private String[] arguments;

  public DeleteCommand(String input) {
    this.arguments = input.split(" ");
  }

  @Override
  public void execute(AlfredUserInterface ui, AlfredStorage storage) throws InvalidInputException,
      InvalidIndexException {
    // check only two arguments
    if (arguments.length != 2) {
      throw new InvalidInputException();
    }
    // check that second argument is numerical
    int taskId;
    try {
      taskId = Integer.valueOf(arguments[1]) - 1;
    } catch (NumberFormatException nfe) {
      throw new InvalidInputException();
    }
    // print out
    String out = "Noted sir. I've removed the following task:\n";
    out += storage.taskToString(taskId);
    ui.sandwichAndPrint(out);

    // modify in storage
    storage.deleteTasK(taskId);

  }

  @Override
  public boolean isExit() {
    return false;
  }
}
