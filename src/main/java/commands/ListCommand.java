package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond(tasks.listing());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
