package duke.command;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class InvalidCommand extends Command {


    public InvalidCommand(String input) {
        super(input);
    }

    public InvalidCommand() {
        super();
    }

    /**
     * Execute the command according to the user's input
     *
     * @param taskList currentTaskList
     * @param ui       ui class that helps to print suitable command
     * @param storage  storage that manage saving and loading data
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
