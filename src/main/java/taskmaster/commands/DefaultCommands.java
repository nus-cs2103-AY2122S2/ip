package taskmaster.commands;
import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

public class DefaultCommands extends Commands {
    private String userInput;

    public DefaultCommands(String userInput) {
        super(userInput);
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions {
        return ui.displayInvalidCommand(this.userInput);
    }
}
