package alfred.command;

import alfred.Alfred;
import alfred.exceptions.MissingInputException;
import alfred.storage.AlfredStorage;
import alfred.task.Task;
import alfred.task.ToDo;
import alfred.ui.AlfredUserInterface;

/**
 * Encapsulates the T0D0 command.
 */
public class ToDoCommand extends Command {

    private final String description;

    /**
     * Constructs a ToDoCommand object.
     *
     * @param input String input from the console. Assumes correct formatting,
     *              where first word is "todo".
     */
    public ToDoCommand(String input) {
        this.description = input.substring(4);
    }

    /**
     * Executes the T0D0 command, by adding a new task to the internal
     * data state of Alfred and printing to console.
     *
     * @param ui      AlfredUserInterface object used by Alfred for handling
     *                interactions with the user.
     * @param storage AlfredStorage object used to handle the internal data
     *                states of Alfred.
     * @throws MissingInputException If there is no valid task description after
     *                               the "todo" keyword.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage)
            throws MissingInputException {
        String out = this.response(ui, storage);
        ui.sandwichAndPrint(out);
    }

    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage) throws MissingInputException {
        // validity check
        if ((this.description.length() < 1) || this.description.split(" ").length == 0) {
            throw new MissingInputException();
        }
        // modify data state
        Task todo = new ToDo(this.description);
        storage.addTask(todo);

        // response
        String out = "Yes sir, I've added this task.\n";
        out += todo.toString() + "\n";
        out += storage.summarizeList();
        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
