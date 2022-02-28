package duke.command;

import java.io.IOException;

import duke.List;
import duke.Storage;
import duke.Ui;


public class SortCommand extends Command {
    public SortCommand() {
        super(false);
    }
    @Override
    public String execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.sort();
        storage.writeToFile("data/duke.txt", taskList);
        return ui.showList(taskList);
    }
}
