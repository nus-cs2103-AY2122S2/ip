package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.markDone(index);
        ui.printMarkDoneTask(taskList.getArrayList().get(index));
        storage.writeToFile("data/duke.txt", taskList);
    }
}
