package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {
    private final static String BYE = "See ya!";

    public ByeCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.printResponse(BYE);
    }
}
