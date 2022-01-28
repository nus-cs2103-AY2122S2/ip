package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command{
    public UnknownCommand(){
        super("unknown");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Sorry, I don't understand that command :/");
    }
}
