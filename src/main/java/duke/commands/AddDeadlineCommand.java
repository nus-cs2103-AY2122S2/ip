package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Command class that adds a deadline task to list.
 */
public class AddDeadlineCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;
    private final String err = "Oh no! The description of deadline cannot be empty... Try again :)\n";
    private final String wrongFormat = "Oh no! The format for deadline task is wrong... Try again :)\n";
    private String response = "";

    /**
     * Constructor that initialises the adding of deadline to list.
     *
     * @param list list of tasks to add this deadline to
     * @param echo the input details of what to be added
     * @param storage store of the list to be added to
     */
    public AddDeadlineCommand(TaskList list, String[] echo, Storage storage) {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
    }

    /**
     * Executes the creation of deadline task.
     *
     * @return a output response for creating a deadline task
     * @throws DukeException thrown when deadline cannot be created
     */
    public String execute() throws DukeException {
        checkValidity(echo.length == 1);
        String description = echo[1];
        checkValidity(description.isEmpty());
        String[] details = description.split(" /by ", 2);
        checkFormat(details.length == 1);
        Deadline curr = new Deadline(details[0], details[1]);
        list.addTask(curr);
        response += Ui.showAddResponse(curr.toString(), list.getSize());
        assert response != null;
        saveToFile(list);
        return response;
    }
    private void checkFormat(boolean bool) throws DukeException {
        if (bool) {
            assert bool : "The format for this deadline should be: deadline example /by yyyy-MM-DD";
            throw new DukeException(wrongFormat);
        }
    }
    private void checkValidity(boolean bool) throws DukeException {
        if (bool) {
            assert bool : "There is no description for this deadline";
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
