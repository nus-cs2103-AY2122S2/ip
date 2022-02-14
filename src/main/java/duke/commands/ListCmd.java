package duke.commands;

import duke.Storage;
import duke.TaskList;


public class ListCmd extends Command{

    @Override
    public String execute(TaskList taskList, Storage storage) {
            return ui.printList(taskList.getList());
    }

}
