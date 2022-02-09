package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class PrintCommand extends Command {
    private final String text;

    public PrintCommand(String s) {
        text = s;
    }

    /**
     * Print the text as respond.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.respond(text);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PrintCommand && ((PrintCommand) o).text.equals(this.text);
    }
}
