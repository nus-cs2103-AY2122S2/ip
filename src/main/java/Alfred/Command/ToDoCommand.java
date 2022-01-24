package Alfred.Command;

import Alfred.Exceptions.MissingInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.Task.Task;
import Alfred.Task.ToDo;
import Alfred.UI.AlfredUserInterface;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String input) {
        this.description = input.substring(4);
    }

    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage)
            throws MissingInputException {
        if ((this.description.length() < 1) || this.description.split(" ").length == 0) {
            throw new MissingInputException();
        }
        // modify data state
        Task todo = new ToDo(this.description);
        storage.addTask(todo, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
