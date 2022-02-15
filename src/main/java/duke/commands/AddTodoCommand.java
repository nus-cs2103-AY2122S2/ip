package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Command class to add a Task of type To do.
 */
public class AddTodoCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;
    private final String err = "Oh no! The description of todo cannot be empty... Try again :)\n";
    private String response = "";

    /**
     * Constructor to initialise the adding of to do task.
     *
     * @param list list of tasks to add this to do to
     * @param echo the input details of what to be added
     * @param storage store of the list to be added to
     */
    public AddTodoCommand(TaskList list, String[] echo, Storage storage) {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
    }

    /**
     * Executes the creation of To Do task.
     *
     * @return a output response for creating a deadline task
     * @throws DukeException thrown when to do cannot be created
     */
    public String execute() throws DukeException {
        checkValidity(echo.length == 1);
        String description = echo[1];
        checkValidity(description.isEmpty());
        Todo curr = new Todo(description);
        list.addTask(curr);
        response = Ui.showAddResponse(curr.toString(), list.getSize());
        assert response != null;
        saveToFile(list);
        return response;
    }
    private void checkValidity(boolean bool) throws DukeException {
        if (bool) {
            assert bool : "The description for to do event is empty";
            throw new DukeException(err);
        }
    }
    private void saveToFile(TaskList list) {
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return false;
    }
}
