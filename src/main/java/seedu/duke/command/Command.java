package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;
import seedu.duke.chatbot.Ui;

import java.io.IOException;

public abstract class Command {
    /**
     *Executes a command.
     * @param tasks is taken in as many commands involve updating the {@link TaskList}
     * @param ui is taken in for the commands to interact with the user after executing
     * @param storage is taken in to update the database after executing a command
     * @return {@link TaskList} which is updated after the command is executed
     * @throws DukeException when there are errors that prevent the command from being executed
     * @throws IOException when there are errors updating the database
     */
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     *Used to signal to Duke when to exit.
     * @return true if user signals they want to exit, false if not
     */
    public abstract boolean isExit();

    /**
     * Used to get the response from Duke after a command has been executed.
     * @return the response from Duke
     */
    public abstract String getResponseAfterCommand(TaskList taskList);
}
