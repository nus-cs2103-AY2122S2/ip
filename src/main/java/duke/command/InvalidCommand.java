package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class InvalidCommand implements Command {

    public InvalidCommand() {}

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("I don't understand your query! Please try again.");
        ui.showLine();
        return false;
    }
}
