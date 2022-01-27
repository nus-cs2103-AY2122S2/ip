package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class PrintCommand extends Command {
    private final String text;

    public PrintCommand(String s) {
        text = s;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond(text);
    }
}
