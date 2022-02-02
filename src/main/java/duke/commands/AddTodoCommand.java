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

    /**
     * Constructor to initialise the adding of to do task.
     *
     * @param list list of tasks to add this to do to
     * @param echo the input details of what to be added
     * @param storage store of the list to be added to
     * @throws DukeException when task cannot be added
     */
    public AddTodoCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
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
        String err = "Oh no! The description of todo cannot be empty... Try again :)\n";
        String response = "";
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        Todo curr = new Todo(description);
        list.addTask(curr);
        response = Ui.showAddResponse(curr.toString(), list.getSize());
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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
