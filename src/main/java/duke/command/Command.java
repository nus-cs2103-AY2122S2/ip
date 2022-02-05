package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * An abstract class Command
 */
public abstract class Command {

    protected String input;

    public Command(String input) {
        this.input = input;
    }

    /**
     * Execute the command according to the user's input
     *
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Check if the command is ByeCommand
     * @return false by default, only when it is bye, it returns true
     */
    public boolean isExit() {
        return false;
    }
}
