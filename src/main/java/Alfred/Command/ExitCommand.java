package Alfred.Command;

import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

public class ExitCommand extends Command {

  @Override
  public void execute(AlfredUserInterface ui, AlfredStorage storage) {
    ui.bye();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
