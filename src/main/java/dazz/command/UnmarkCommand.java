package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        taskList.unmark(this.index);
//        ui.showUnmark(taskList.getTask(this.index));
        String message = ui.messageForUnmark(taskList.getTask(this.index));
        storage.updateList(taskList);
        return message;
    }
}
