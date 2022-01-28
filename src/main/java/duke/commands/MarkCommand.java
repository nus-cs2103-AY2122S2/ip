package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public abstract class MarkCommand extends Command{
    public MarkCommand(String userInput){
        super(userInput);
    }

    public static MarkCommand of(String userInput){
        if (userInput.startsWith("mark")) {
            return new MarkDoneCommand(userInput);
        } else {
            return new MarkUndoneCommand(userInput);
        }
    }

    public abstract boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException;
}
