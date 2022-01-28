package duke;

import java.io.IOException;

public class ListCommand extends Command{

    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printList(taskList.convertListToString());
        storage.rewriteTask(taskList);
    }

}
