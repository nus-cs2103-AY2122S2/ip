package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * Constructor to initialize an instance of ExitCommand class.
     */
    public ExitCommand() {
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True as the command is an Exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command of exiting the program, and then returns
     * the response message.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        // Reset the List of filteredTasks when ExitCommand is executed
        // This will clear the List of filteredTasks
        taskList.resetFilteredTasks();

        String response = Ui.EXIT_MESSAGE;
        assert !response.equals("") : "Exit response should not be empty";

        return response;
    }
}
