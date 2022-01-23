package alfred.command;

import alfred.exceptions.MissingInputException;
import alfred.storage.AlfredStorage;
import alfred.task.Task;
import alfred.task.ToDo;
import alfred.ui.AlfredUserInterface;

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
